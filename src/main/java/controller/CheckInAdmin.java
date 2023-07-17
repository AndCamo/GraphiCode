package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;

import java.io.IOException;
import java.sql.SQLException;


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
            switch (type) {
                case "dashboard":
                    address = "/WEB-INF/results/admin/admin-dashboard.jsp";
                    break;
                case "add-product":
                    address = "/WEB-INF/results/admin/add-product.jsp";
                    break;
                case "show-user":
                    address = "/get-users";
                    break;
                case "edit-user":
                    address = "/WEB-INF/results/admin/edit-user-page.jsp";
                    break;
                case "edit-product": {
                    address = "/WEB-INF/results/admin/edit-product-page.jsp";
                    String productCode = request.getParameter("product-code");
                    ProductDAO service = new ProductDAO();
                    try {
                        ProductBean product = service.doRetrieveById(productCode);
                        request.setAttribute("productToEdit", product);
                    } catch (SQLException ex) {
                        request.setAttribute("type", "sqlError");
                        request.setAttribute("msg", "Errore durante il caricamento nel database");
                        request.setAttribute("redirect", "/index.jsp");
                        address = "/WEB-INF/results/confirmPage.jsp";
                        ex.printStackTrace();
                    }
                    break;
                }
            }
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
