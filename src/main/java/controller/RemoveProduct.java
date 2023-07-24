package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "removeProductServlet", value = "/remove-cart-product")
public class RemoveProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("productId"));
        UserBean currentUser = (UserBean) request.getSession().getAttribute("user");
        CartDAO cartService = new CartDAO();
        CartBean cart = (CartBean) request.getSession().getAttribute("cart");

        CartItemDAO cartItemService = new CartItemDAO();
        RequestDispatcher dispatcher;
        String address = "";

        try {
            CartItemBean productToRemove = cartItemService.doRetrieveById(id);
            if (productToRemove == null){
                address = "/show-cart";
            }
            else if (cart != null && cart.getId() == productToRemove.getCartId()){
                cart.removeProduct(productToRemove);
                cartItemService.doDeleteById(id);
                address = "/show-cart";
                cartService.updateArticleNumber(cart, cart.getProductCount());
            } else {
                request.setAttribute("type", "alert");
                request.setAttribute("msg", "Qualcosa è andato storto.");
                request.setAttribute("redirect", "/show-cart");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
        } catch (SQLException ex){
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento dal database");
            request.setAttribute("redirect", "/show-cart");
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
