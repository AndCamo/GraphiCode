package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartBean;
import model.CartDAO;
import model.UserBean;
import model.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "updateUserServlet", value = "/update-user")
public class UpdateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        String msg = "";

        String userName = request.getParameter("firstName");
        String userSurname = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String nation = request.getParameter("nation");
        String phoneNumber = request.getParameter("phoneNumber");
        String eMail = request.getParameter("email");
        String password = request.getParameter("password");
        String admin = request.getParameter("isAdmin");
        boolean isAdmin = (admin == null) ? false : true;


        UserBean userToUpdate = new UserBean(userName, userSurname,
                eMail, password, phoneNumber, nation, birthDate);
        userToUpdate.setAdmin(isAdmin);

        String address = "";
        UserDAO service = new UserDAO();
        boolean matchFlag = userToUpdate.checkUserData();
        Matcher matcher = null;

        final Pattern email_regex = Pattern.compile("^[a-zA-Z\\d._%-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,20}$");
        matcher = email_regex.matcher(eMail);
        if (!matcher.find() || eMail.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATA EMAIL");
        }
        final Pattern password_regex = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).+$");
        matcher = password_regex.matcher(password);
        if (!matcher.find() || password.isEmpty()){
            matchFlag = false;
            System.out.println(password);
            System.out.println("SBAGLIATA PASS");
        }

        try {
            if (currentUser != null && currentUser.isAdmin()){
                if (matchFlag) {
                    service.doUpdate(userToUpdate);
                    request.setAttribute("type", "success-edit");
                    request.setAttribute("msg", "Modifica avvenuta con successo");
                    request.setAttribute("redirect", "/index.jsp");
                    request.getSession().setAttribute("user", userToUpdate);
                } else {
                    request.setAttribute("type", "registration-error");
                    request.setAttribute("msg", "Errore nei dati inseriti");
                    request.setAttribute("redirect", "/index.jsp");
                    address = "/WEB-INF/results/confirmPage.jsp";
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        }catch(SQLException ex) {
            request.setAttribute("type", "sqlError");
            request.setAttribute("msg", "Errore durante il caricamento nel database");
            request.setAttribute("redirect", "/index.jsp");
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
