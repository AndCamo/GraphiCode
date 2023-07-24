package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductDAO;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "getCategoriesServlet", value = "/get-categories")
public class GetCategories extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        ProductDAO productService = new ProductDAO();
        PrintWriter out = response.getWriter();
        try {
            List<String> categories = productService.doRetrieveCategories();
            JSONArray jsonList = new JSONArray(categories);
            out.write(jsonList.toString());
        } catch (SQLException e) {
            out.write("sqlError");
            throw new RuntimeException(e);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
