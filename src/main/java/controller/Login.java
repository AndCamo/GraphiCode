package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import model.CartDAO;
import model.UserBean;
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "loginServlet", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Pattern email_regex = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
        final Pattern password_regex = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$");
        boolean matchFlag = true;
        Matcher matcher = null;

        String eMail = request.getParameter("email");
        matcher = email_regex.matcher(eMail);
        if (!matcher.find() || eMail.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATA EMAIL");
        }
        String password = request.getParameter("password");

        matcher = password_regex.matcher(password);
        if (!matcher.find() || password.isEmpty()){
            matchFlag = false;
            System.out.println(password);
            System.out.println("SBAGLIATA PASS");
        }

        String address = "";
        UserDAO service = new UserDAO();
        try {
            if (matchFlag) {
                UserBean user = service.doRetrieveByEmailAndPassword(eMail, password);
                if (user != null) {
                    request.setAttribute("type", "success-login");
                    request.setAttribute("msg", "Login avvenuto con successo");
                    request.setAttribute("redirect", "/index.jsp");
                    address = "/WEB-INF/results/confirmPage.jsp";
                    request.getSession().setAttribute("user", user);

                    //AGGIUNGI CARRELLO

                    CartDAO cartService = new CartDAO();
                    CartBean newCart = cartService.doRetrieveByUserID(user.getId());
                    request.getSession().setAttribute("cart", newCart);
                } else {
                    request.setAttribute("type", "login-error");
                    request.setAttribute("msg", "Credenziali sbagliate!");
                    request.setAttribute("redirect", "/index.jsp");
                    address = "login-page.jsp";
                }
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante la ricerca nel database");
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
