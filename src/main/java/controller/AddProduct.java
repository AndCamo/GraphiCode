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
@WebServlet(name = "addProductServlet", value = "/add-product")
public class AddProduct extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        ProductDAO productService = new ProductDAO();
        String address = "";

        if (currentUser != null && currentUser.isAdmin()){

            String productCode = request.getParameter("productCode");
            String name = request.getParameter("productName");
            Double price = Double.parseDouble(request.getParameter("productPrice"));
            int sale = Integer.parseInt(request.getParameter("productSale"));
            String catergory = request.getParameter("category");
            String description = request.getParameter("description");

            Part part = request.getPart("image");
            String subpath;
            if (!part.getSubmittedFileName().isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "product-image";
                String imagepath = uploadPath + File.separator + part.getSubmittedFileName();
                part.write(imagepath);
                subpath = "./assets/product-image/" + part.getSubmittedFileName();
            }
            else {
                subpath = "./assets/product-image/noimage.png";
            }
            ProductBean newProduct = new ProductBean(productCode, name, catergory, subpath, description, price);
            newProduct.setSale(sale);

            try {
                boolean matchflag = newProduct.checkProductValues();
                if (matchflag){
                    if (productService.isAlreadyRegistered(productCode)){
                        request.setAttribute("type", "alert");
                        request.setAttribute("msg", "Esiste già un prodotto con questo codice.");
                        request.setAttribute("redirect", "/check-in?type=dashboard");
                        address = "/WEB-INF/results/confirmPage.jsp";
                    } else {
                        productService.doSave(newProduct);
                        request.setAttribute("type", "success-insert");
                        request.setAttribute("msg", "Caricamento prodotto avvenuto con successo");
                        request.setAttribute("redirect", "/check-in?type=dashboard");
                        address = "/WEB-INF/results/confirmPage.jsp";
                    }
                } else {
                    request.setAttribute("type", "add-product-error");
                    request.setAttribute("msg", "Errore nei dati inseriti");
                    request.setAttribute("redirect", "/check-in?type=add-product");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            } catch (SQLException ex) {
                request.setAttribute("type", "sqlError");
                request.setAttribute("msg", "Errore durante il caricamento nel database");
                request.setAttribute("redirect", "/check-in?type=dashboard");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
