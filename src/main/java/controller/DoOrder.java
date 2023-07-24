package controller;


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
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "doOrderServlet", value = "/do-order")
public class DoOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        CartBean cart = (CartBean) session.getAttribute("cart");
        CartDAO cartService = new CartDAO();
        OrderDAO orderService = new OrderDAO();
        OrderItemDAO orderItemService = new OrderItemDAO();

        CartItemDAO cartItemService = new CartItemDAO();
        ProductDAO productService = new ProductDAO();
        PaymentDAO paymentService = new PaymentDAO();

        String address = "";
        RequestDispatcher dispatcher;

        try {
            if (currentUser == null){
                request.setAttribute("type", "alert");
                request.setAttribute("msg", "Per effettuare un ordine devi essere registrato!");
                request.setAttribute("redirect", "/login-page.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
            else if(currentUser.isAdmin() || cart == null){
                request.setAttribute("type", "alert");
                request.setAttribute("msg", "Qualcosa è andato storto.");
                request.setAttribute("redirect", "/index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
            else {
                String cardNumber = request.getParameter("cardNumber");
                String cardHolder = request.getParameter("cardHolder");
                String expireDate = request.getParameter("expireDate");
                String cvc = request.getParameter("credit-cvc");

                PaymentBean payment = new PaymentBean(cardNumber, cardHolder, cvc, expireDate, currentUser.getId());
                if (payment.checkPaymentValue()){
                    //PAGAMENTO APPROVATO
                    paymentService.doSave(payment);

                    String date = LocalDate.now().toString();
                    System.out.println(date);
                    OrderBean newOrder = new OrderBean(currentUser.getId(), payment.getId(), date, 0);
                    orderService.doSave(newOrder);
                    List<CartItemBean> cartItemList = cartItemService.doRetrieveByCartId(cart.getId());
                    double tmpTotal = 0.0;
                    if (cartItemList != null && cartItemList.size() > 0){
                        for (CartItemBean tmpCartItem : cartItemList){
                            OrderItemBean orderItem = new OrderItemBean();
                            orderItem.setOrderNumber(newOrder.getOrderNumber());
                            orderItem.setBriefingId(tmpCartItem.getBriefingId());
                            orderItem.setQuantity(tmpCartItem.getQuantity());
                            orderItem.setProductCode(tmpCartItem.getProductCode());
                            ProductBean product = productService.doRetrieveById(tmpCartItem.getProductCode());
                            orderItem.setPrice(product.getDiscountedPrice());
                            tmpTotal += (product.getDiscountedPrice() * tmpCartItem.getQuantity());

                            orderItemService.doSave(orderItem);


                            cart.removeProduct(tmpCartItem);
                            cartItemService.doDeleteById(tmpCartItem.getId());
                            cartService.updateArticleNumber(cart, cart.getProductCount());
                        }
                        orderService.doUpdateTotal(newOrder, tmpTotal);

                        request.setAttribute("type", "success-order");
                        request.setAttribute("msg", "Ordine effettuato con successo! Procedi per visualizzare i dettagli.");
                        request.setAttribute("redirect", "/index.jsp");
                        address = "/WEB-INF/results/confirmPage.jsp";
                    } else {
                        request.setAttribute("type", "error");
                        request.setAttribute("msg", "Impossibile effettuare l'ordine. Il tuo carrello è vuoto");
                        request.setAttribute("redirect", "/index.jsp");
                        address = "/WEB-INF/results/confirmPage.jsp";
                    }
                } else {
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "I dati del pagamento non sono corretti!.");
                    request.setAttribute("redirect", "/index.jsp");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }

            }
        } catch (SQLException ex){
            ex.printStackTrace();
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento nel database");
            request.setAttribute("redirect", "/show-cart");
            address = "/WEB-INF/results/confirmPage.jsp";
        }

        dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
