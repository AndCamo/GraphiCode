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

@WebServlet(name = "showOrderItemInfo", value = "/show-orderitem-info")
public class ShowOrderItemInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String paramter = request.getParameter("orderItemId");
        OrderItemDAO orderItemService = new OrderItemDAO();
        ProductDAO productService = new ProductDAO();
        OrderDAO orderService = new OrderDAO();
        BriefingDAO briefingService = new BriefingDAO();

        UserBean user = (UserBean) session.getAttribute("user");

        String address = "";
        RequestDispatcher dispatcher;

        int orderItemId = -1;
        if (paramter != null)
            orderItemId = Integer.parseInt(paramter);

        try {
            OrderItemBean orderItem = orderItemService.doRetrieveById(orderItemId);
            if (orderItem != null) {
                OrderBean order = orderService.doRetrieveById(orderItem.getId());

                if ((user != null && user.isAdmin()) ||
                        (order != null && user != null && order.getUserId() == user.getId())) {
                    ProductBean product = productService.doRetrieveById(orderItem.getProductCode());
                    BriefingBean briefing = briefingService.doRetrieveById(orderItem.getBriefingId());

                    request.setAttribute("product", product);
                    request.setAttribute("briefing", briefing);
                    request.setAttribute("orderItem", orderItem);
                    address = "/WEB-INF/results/order-item-info.jsp";
                } else {
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Qualcosa è andato storto.");
                    request.setAttribute("redirect", "/index.jsp");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            } else {
                request.setAttribute("type", "error");
                request.setAttribute("msg", "Ordine non esistente.");
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
