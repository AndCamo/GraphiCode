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


@WebServlet(name = "getProductInfo", value = "/product-info")
public class ProductInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        String address = "";
        String codeToGet = (request.getParameter("productCode")) != null ? request.getParameter("productCode") : "AAA000";

        if (currentUser != null && currentUser.isAdmin()){
            ProductDAO productService = new ProductDAO();
            try {
                ProductBean product = productService.doRetrieveById(codeToGet);
                request.setAttribute("product", product);
                address = "/WEB-INF/results/product-info.jsp";
            } catch (SQLException ex){
                request.setAttribute("type", "sqlError");
                request.setAttribute("msg", "Errore durante il caricamento dal database");
                request.setAttribute("redirect", "/index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
                ex.printStackTrace();
            }
        } else {
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
