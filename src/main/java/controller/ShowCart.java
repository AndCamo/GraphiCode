package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "showCartServlet", value = "/show-cart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        CartBean cart = (CartBean) session.getAttribute("cart");
        CartDAO cartService = new CartDAO();
        JSONArray cartItemArray = new JSONArray();
        String address = "";
        RequestDispatcher dispatcher;
        double total = 0.00;

        try {
            if (currentUser != null && currentUser.isAdmin()){
                response.sendRedirect("index.jsp");
            } else if (cart != null){
                    ProductDAO productService = new ProductDAO();
                    for (CartItemBean tmpItem : cart.getProductList()){
                        ProductBean product = productService.doRetrieveById(tmpItem.getProductCode());
                        total += (product.getDiscountedPrice() * tmpItem.getQuantity());
                        JSONObject newItem = new JSONObject();
                        newItem.put("productCode", tmpItem.getProductCode());
                        newItem.put("cartItemId", tmpItem.getId());
                        newItem.put("productImage", product.getImage());
                        newItem.put("productName", product.getName());
                        newItem.put("category", product.getCategory());
                        newItem.put("price", product.getDiscountedPrice());
                        newItem.put("quantity", tmpItem.getQuantity());
                        newItem.put("briefingId", tmpItem.getBriefingId());
                        cartItemArray.put(newItem);
                    }
            }
            request.setAttribute("total", total);
            request.setAttribute("products", cartItemArray);
            address = "/WEB-INF/results/cart.jsp";
        } catch (SQLException ex) {
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento dal database");
            request.setAttribute("redirect", "/index.jsp");
            address = "/WEB-INF/results/confirmPage.jsp";
            ex.printStackTrace();
        }
        dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
