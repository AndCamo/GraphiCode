package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

    static final String DB_URL = "jdbc:mysql://localhost:3306/GraphiCode";
    static final String USER = "root";
    static final String PASS = "Andsql10";



    private static Connection conn = null;
    private static Statement stmt = null;

    public void startConnection() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }

    public void closeConnection() throws SQLException{
        conn.close();
    }

}
