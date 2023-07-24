package controller;

import exception.InvalidAddToCartException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "addProductToCartServlet", value = "/add-to-cart")
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        UserDAO userService = new UserDAO();
        CartBean cart = (CartBean) session.getAttribute("cart");
        CartDAO cartService = new CartDAO();

        CartItemBean newCartItem = new CartItemBean();
        CartItemDAO cartItemService = new CartItemDAO();

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String productCode = request.getParameter("productId");

        ProductDAO productService = new ProductDAO();

        BriefingDAO briefingService = new BriefingDAO();

        String address = "";
        RequestDispatcher dispatcher;

        try {
            if (currentUser != null && currentUser.isAdmin()) {
                throw new InvalidAddToCartException();
            } else { //USER NON È ADMIN
                if (cart == null) {
                    cart = new CartBean(); //NUOVO CARRELLO PER UTENTE NON REGISTRATO
                    cartService.doSave(cart);
                    session.setAttribute("cart", cart);
                }
                int userId = (currentUser == null) ? -1 : currentUser.getId(); //SERVE PER IL BRIEFING
                ProductBean product = productService.doRetrieveById(productCode);
                if (product == null)
                    throw new InvalidAddToCartException();
                else if(product.isPersonalized()){
                    //SAVE BRIEFING
                    String target = request.getParameter("target");
                    String style = request.getParameter("style");
                    String goals = request.getParameter("goals");
                    List<String> palette = new ArrayList<>();
                    palette.add(request.getParameter("palette1"));
                    palette.add(request.getParameter("palette2"));
                    String note = request.getParameter("notes");
                    BriefingBean briefing = new BriefingBean(userId, product.getCode(), target,
                            style, goals, note, palette.toString());
                    if (!briefing.checkBriefingValue()) {
                        //DATI DEL BRIEFING NON CORRETTI
                        request.setAttribute("type", "add-cart-error");
                        request.setAttribute("msg", "Errore nei dati del briefing");
                        request.setAttribute("redirect", "/show-catalog?filter=all");
                        address = "/WEB-INF/results/confirmPage.jsp";
                        dispatcher = request.getRequestDispatcher(address);
                        dispatcher.forward(request, response);
                    } else {
                        //AGGIUNTA PRODOTTO CON BRIEFING
                        briefingService.doSave(briefing);
                        newCartItem = new CartItemBean(product.getCode(), quantity, cart.getId());
                        newCartItem.setBriefingId(briefing.getId());
                    }
                } else {
                    //PRODOTTO SENZA PERSONALIZZAZIONE
                    newCartItem = new CartItemBean(product.getCode(), 1, cart.getId());
                    newCartItem.setBriefingId(-1);
                }
                cartItemService.doSave(newCartItem);
                cart.addProduct(newCartItem);
                cartService.updateArticleNumber(cart, cart.getProductCount());
                request.setAttribute("type", "success-add");
                request.setAttribute("msg", "Prodotto aggiunto al carrello");
                request.setAttribute("redirect", "/show-catalog?filter=all");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
        }catch (SQLException ex){
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento nel database");
            request.setAttribute("redirect", "/show-catalog?filter=all");
            address = "/WEB-INF/results/confirmPage.jsp";
            ex.printStackTrace();
        } catch (IllegalArgumentException ex){
            request.setAttribute("type", "addError");
            request.setAttribute("msg", "Aggiunta al carrello non valida");
            request.setAttribute("redirect", "/show-catalog?filter=all");
            address = "/WEB-INF/results/confirmPage.jsp";
            ex.printStackTrace();
        }
        dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
