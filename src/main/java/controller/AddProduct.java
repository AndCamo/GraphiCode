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


@WebServlet(name = "addProductServlet", value = "/add-product")
public class AddProduct extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");


        String productCode = request.getParameter("productCode");
        String name = request.getParameter("productName");
        Double price = Double.parseDouble(request.getParameter("productPrice"));
        int sale = Integer.parseInt(request.getParameter("productSale"));
        String catergory = request.getParameter("catergory");
        String description = request.getParameter("description");
        String image = request.getParameter("image");


        boolean matchFlag = true;
        String address = "";
        ProductDAO productService = new ProductDAO();
        try {
            if (matchFlag){
                if (productService.isAlreadyRegistered(productCode)){
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Esiste già un prodotto con questo codice.");
                    request.setAttribute("redirect", "/check-in?type=dashboard");
                    address = "/WEB-INF/results/confirmPage.jsp";
                } else {
                    ProductBean newProduct = new ProductBean(productCode, name, catergory, image,
                            description, price);
                    newProduct.setSale(sale);

                    productService.doSave(newProduct);
                    request.setAttribute("type", "success-insert");
                    request.setAttribute("msg", "Caricamento prodotto avvenuto con successo");
                    request.setAttribute("redirect", "/check-in?type=dashboard");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            } else {
                request.setAttribute("type", "add-product-error");
                request.setAttribute("msg", "Errore nei dati inseriti");
                request.setAttribute("redirect", "/check-in?type=dashboard");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
        } catch (SQLException ex){
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento nel database");
            request.setAttribute("redirect", "/check-in?type=dashboard");
            address = "/WEB-INF/results/confirmPage.jsp";
            ex.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
