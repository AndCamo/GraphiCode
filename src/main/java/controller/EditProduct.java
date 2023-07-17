package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.ProductBean;
import model.ProductDAO;
import model.UserBean;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 2024 * 2024,
        maxFileSize = 2024 * 2024 * 5,
        maxRequestSize = 2024 * 2024 * 5 * 5)

@WebServlet(name = "editProductServlet", value = "/edit-product")
public class EditProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        ProductDAO productService = new ProductDAO();
        String address = "";

        if (currentUser != null && currentUser.isAdmin()){
            try {
                String productCode = request.getParameter("productCode");
                String name = request.getParameter("productName");
                Double price = Double.parseDouble(request.getParameter("productPrice"));
                int sale = Integer.parseInt(request.getParameter("productSale"));
                String catergory = request.getParameter("category");
                String description = request.getParameter("description");

                Part part = request.getPart("image");

                String subpath;
                if (!part.getSubmittedFileName().isEmpty()) {
                    String uploadPath = getServletContext().getRealPath("") + "/assets/product-image";
                    String imagepath = uploadPath + File.separator + part.getSubmittedFileName();
                    part.write(imagepath);
                    subpath = "./assets/product-image/" + part.getSubmittedFileName();
                }
                else {
                    subpath = productService.doRetrieveById(productCode).getImage();
                }
                ProductBean newProduct = new ProductBean(productCode, name, catergory, subpath, description, price);
                newProduct.setSale(sale);

                boolean matchflag = newProduct.checkProductValues();
                if (matchflag){
                    productService.doUpdate(newProduct);
                    request.setAttribute("product", newProduct);
                    address = "/WEB-INF/results/product-info.jsp";
                } else {
                    request.setAttribute("type", "edit-error");
                    request.setAttribute("msg", "Errore nei dati inseriti");
                    request.setAttribute("redirect", "/get-products");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            }catch (SQLException ex){
                request.setAttribute("type", "sqlError");
                request.setAttribute("msg", "Errore durante il caricamento nel database");
                request.setAttribute("redirect", "/check-in?type=dashboard");
                address = "/WEB-INF/results/confirmPage.jsp";
                ex.printStackTrace();
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
