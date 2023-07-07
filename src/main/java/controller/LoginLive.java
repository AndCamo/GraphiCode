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
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "liveLoginServlet", value = "/live-login")
public class LoginLive extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eMail = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(eMail + " " + password);
        UserDAO service = new UserDAO();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        try {
            UserBean user = service.doRetrieveByEmailAndPassword(eMail, password);
            if (user != null){
                out.write("login-success");
                out.close();
            } else {
                out.write("login-error");
                out.close();
            }
        }catch (SQLException ex){
            out.write("sql-error");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
