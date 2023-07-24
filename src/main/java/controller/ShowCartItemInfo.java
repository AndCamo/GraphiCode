package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "showCartItemInfo", value = "/show-cartitem-info")
public class ShowCartItemInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String paramter = request.getParameter("cartItemId");
        CartItemDAO cartItemService = new CartItemDAO();
        ProductDAO productService = new ProductDAO();
        BriefingDAO briefingService = new BriefingDAO();

        CartBean cart = (CartBean) session.getAttribute("cart");
        UserBean user = (UserBean) session.getAttribute("user");

        String address = "";
        RequestDispatcher dispatcher;

        int cartItemid = -1;
        if (paramter != null)
            cartItemid = Integer.parseInt(paramter);

        try {
            CartItemBean cartItem = cartItemService.doRetrieveById(cartItemid);

            if ((user != null && user.isAdmin()) ||
                    (cart != null && cartItem != null && cart.getId() == cartItem.getCartId())){
                ProductBean product = productService.doRetrieveById(cartItem.getProductCode());
                BriefingBean briefing = briefingService.doRetrieveById(cartItem.getBriefingId());

                request.setAttribute("product", product);
                request.setAttribute("briefing", briefing);
                request.setAttribute("cartItem", cartItem);

                address = "/WEB-INF/results/cart-item-info.jsp";
            } else {
                request.setAttribute("type", "alert");
                request.setAttribute("msg", "Qualcosa è andato storto.");
                request.setAttribute("redirect", "/index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
