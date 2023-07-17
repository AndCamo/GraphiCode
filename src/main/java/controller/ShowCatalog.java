package controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "showCatalogServlet", value = "/show-catalog")
public class ShowCatalog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productService = new ProductDAO();
        String address = "";
        try {
            List<ProductBean> productList = new ArrayList<>();
            String productFilter = request.getParameter("filter");
            if (productFilter.equalsIgnoreCase("all"))
                productList = productService.doRetrieveAll();
            else
                productList = productService.doRetrieveByCategory(productFilter);

            List<String> categories = productService.doRetrieveCategories();
            request.setAttribute("filter", productFilter);
            request.setAttribute("categories", categories);
            request.setAttribute("products", productList);
            address = "/WEB-INF/results/catalog.jsp";
        } catch (SQLException ex) {
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
