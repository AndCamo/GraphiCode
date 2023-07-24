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
import java.util.List;

@WebServlet(name = "getOrderServlet", value = "/get-order")
public class GetOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        String address = "";
        JSONArray orderList = new JSONArray();


        if (currentUser != null && currentUser.isAdmin()){
            OrderDAO orderService = new OrderDAO();
            try {
                UserDAO userService = new UserDAO();
                List<OrderBean> orders = orderService.doRetrieveAll();
                for (OrderBean tmpOrder : orders){
                    JSONObject newOrder = new JSONObject();
                    newOrder.put("orderId", tmpOrder.getOrderNumber());
                    newOrder.put("userId", tmpOrder.getUserId());
                    UserBean user = userService.doRetrieveById(tmpOrder.getUserId());
                    newOrder.put("userName", user.getName());
                    newOrder.put("userSurname", user.getSurname());

                    orderList.put(newOrder);
                }
                request.setAttribute("orderList", orderList);
                address = "/WEB-INF/results/show-orderList.jsp";
            } catch (SQLException ex) {
                request.setAttribute("type", "sqlError");
                request.setAttribute("msg", "Errore durante il caricamento dal database");
                request.setAttribute("redirect", "/index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
                ex.printStackTrace();
            }
        }
        else {
            request.setAttribute("type", "alert");
            request.setAttribute("msg", "Qualcosa è andato storto.");
            request.setAttribute("redirect", "/index.jsp");
            address = "/WEB-INF/results/confirmPage.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
