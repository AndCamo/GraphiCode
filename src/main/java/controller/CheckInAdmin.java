package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserBean;

import java.io.IOException;


@WebServlet(name = "checkInAdmin", value = "/check-in")
public class CheckInAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean currentUser = new UserBean();
        HttpSession session = request.getSession();
        currentUser = (UserBean) session.getAttribute("user");
        String type = request.getParameter("type");
        String address = "";
        if (currentUser != null && currentUser.isAdmin()){
            if (type.equals("dashboard"))
                address = "/WEB-INF/results/admin/admin-dashboard.jsp";
            else if (type.equals("add-product"))
                address = "/WEB-INF/results/admin/add-product.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
