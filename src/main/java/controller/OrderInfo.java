package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getOrderInfo", value = "/order-info")
public class OrderInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        OrderDAO orderService = new OrderDAO();
        UserDAO userService = new UserDAO();
        OrderItemDAO orderItemService = new OrderItemDAO();
        int orderNumber = (request.getParameter("orderNumber")) != null ? Integer.parseInt(request.getParameter("orderNumber")) : -1;

        String address = "";
        RequestDispatcher dispatcher;

        try {

            OrderBean order = orderService.doRetrieveById(orderNumber);
            if (order != null && currentUser != null &&
                    (currentUser.isAdmin() || currentUser.getId() == order.getUserId())){
                UserBean client = userService.doRetrieveById(order.getUserId());
                String clientName = client.getName() + " " + client.getSurname();
                List<OrderItemBean> orderItems = orderItemService.doRetrieveByOrderId(order.getOrderNumber());

                request.setAttribute("orderItems", orderItems);
                request.setAttribute("client", clientName);
                request.setAttribute("order", order);
                address = "/WEB-INF/results/order-info.jsp";
            } else {
                request.setAttribute("type", "alert");
                request.setAttribute("msg", "Qualcosa è andato storto.");
                request.setAttribute("redirect", "/index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
            }

        }catch (SQLException ex){
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
