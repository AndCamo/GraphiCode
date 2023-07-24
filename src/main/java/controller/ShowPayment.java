package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartBean;
import model.UserBean;

import java.io.IOException;

@WebServlet(name = "showPaymentServlet", value = "/show-payment")
public class ShowPayment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        CartBean cart = (CartBean) session.getAttribute("cart");
        String address = "";
        RequestDispatcher dispatcher;
        if (user == null || cart == null || cart.getProductCount() == 0){
            address = "/login-page.jsp";
        } else {
            address = "/WEB-INF/results/payment-page.jsp";
        }

        dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
