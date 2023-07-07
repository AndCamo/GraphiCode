package controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartBean;
import model.CartDAO;
import model.UserBean;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "logoutServlet", value = "/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        CartBean cartBean = (CartBean) session.getAttribute("cart");
        CartDAO serviceCart = new CartDAO();

        if (user != null){
            if (!user.isAdmin()) {
                try {
                    serviceCart.updateArticleNumber(cartBean, cartBean.getProductCount());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            // SALVARE TUTTI I CARTITEM CON UN FOR
        }
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
