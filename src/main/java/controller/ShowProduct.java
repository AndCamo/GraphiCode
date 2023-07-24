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


@WebServlet(name = "showProdutServlet", value = "/show-product")
public class ShowProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        String codeToGet = (request.getParameter("productCode")) != null ? request.getParameter("productCode") : "null";
        String address = "";

        try {
            ProductDAO productService = new ProductDAO();
            ProductBean product = productService.doRetrieveById(codeToGet);
            if (product != null){
                request.setAttribute("product", product);
                if (currentUser != null && currentUser.isAdmin())
                    address = "/WEB-INF/results/product-info.jsp";
                else
                    address = "/WEB-INF/results/product.jsp";
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

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
