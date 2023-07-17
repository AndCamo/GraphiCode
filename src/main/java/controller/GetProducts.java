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
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "getProductServlet", value = "/get-products")
public class GetProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        String address = "";

        if (currentUser != null && currentUser.isAdmin()){
            ProductDAO productService = new ProductDAO();
            try {
                List<ProductBean> productList = productService.doRetrieveAll();
                request.setAttribute("productList", productList);
                address = "/WEB-INF/results/show-productList.jsp";
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
