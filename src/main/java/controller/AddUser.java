package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserBean;
import model.UserDAO;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add-user")
public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final Pattern password_regex = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$");
        final Pattern email_regex = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
        final Pattern phone_regex = Pattern.compile("^\\d{10}$");
        Matcher matcher = null;
        boolean matchFlag = true;

        String msg = "";


        String userName = request.getParameter("firstName");
        String userSurname = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String nation = request.getParameter("nation");

        String phoneNumber = request.getParameter("phoneNumber");
        matcher = phone_regex.matcher(phoneNumber);
        if (!matcher.find()){
            matchFlag = false;
            System.out.println("SBAGLIATO NUMERO");
        }

        String eMail = request.getParameter("email");
        matcher = email_regex.matcher(eMail);
        if (!matcher.find()){
            matchFlag = false;
            System.out.println("SBAGLIATA EMAIL");
        }
        String password = request.getParameter("password");
        matcher = password_regex.matcher(password);
        if (!matcher.find()){
            matchFlag = false;
            System.out.println("SBAGLIATA PASS");
        }

        String address = "";
        UserDAO service = new UserDAO();

        try {
            if (matchFlag){
                if (service.isAlreadyRegistered(eMail)) {
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Esiste già un utente con questa eMail.");
                    request.setAttribute("redirect", "index.jsp");
                    address = "/WEB-INF/results/registration-page.jsp";
                } else {
                    UserBean newUser = new UserBean(userName, userSurname,
                            eMail, password, phoneNumber, nation, birthDate);
                    service.doSave(newUser);
                    request.setAttribute("type", "success");
                    request.setAttribute("msg", "Registrazione avvenuta con successo");
                    request.setAttribute("redirect", "index.jsp");
                    request.setAttribute("newUser", newUser);
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            }
            else {
                request.setAttribute("type", "error");
                request.setAttribute("msg", "Errore nei dati inseriti");
                request.setAttribute("redirect", "index.jsp");
                address = "/WEB-INF/results/confirmPage.jsp";
            }
        }catch (SQLException ex){
            request.setAttribute("type", "sqlError");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
