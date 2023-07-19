package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserBean;
import model.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet(name = "setAdminServlet", value = "/set-admin")
public class SetAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean currentUser = (UserBean) session.getAttribute("user");
        PrintWriter out = response.getWriter();
        int idToEdit = Integer.parseInt(request.getParameter("userId"));
        String esit = "";
        boolean status = false;
        if (currentUser != null && currentUser.isAdmin() && currentUser.getId() != idToEdit){
            UserDAO userService = new UserDAO();
            try {
                UserBean tmpUser = userService.doRetrieveById(idToEdit);
                userService.changeAdminStatus(tmpUser, !tmpUser.isAdmin());
                status = !tmpUser.isAdmin();
                esit = "set-success";
            } catch (SQLException ex) {
                esit = "sql-error";
                ex.printStackTrace();
            }
        }
        else {
            esit = "Error";
        }

        out.write(
                "{" +
                        "\"esit\": " + "\"" + esit + "\",\n" +
                        "\"isAdmin\":" + status +
                "}");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
