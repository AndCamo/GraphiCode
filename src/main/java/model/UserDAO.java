package model;

import java.sql.*;

public class UserDAO {
    public void doSave(UserBean newUser) throws SQLException {
            Connection connection = ConnectionPool.getConnection();
            System.out.println("Inserisco: " + newUser.getName() + " " + newUser.getSurname());
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Utente (nome, cognome, eMail, passkey, telefono, data_di_nascita, nazione, isAdmin) " +
                            "VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newUser.getName());
            ps.setString(2, newUser.getSurname());
            ps.setString(3, newUser.geteMail());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getPhoneNumber());
            ps.setString(6, newUser.getBirthDate());
            ps.setString(7, newUser.getNation());
            ps.setBoolean(8, newUser.isAdmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            newUser.setId(id);
            ConnectionPool.closeConnection();
    }

    public boolean isAlreadyRegistered(String eMail) throws SQLException {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT eMail FROM Utente WHERE Email=?");
            ps.setString(1, eMail);
            ResultSet rs = ps.executeQuery();
            return rs.next();
    }

    public UserBean doRetrieveByEmailAndPassword(String email, String password) throws SQLException {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Utente " +
                            "WHERE eMail=? AND passkey=SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            System.out.println("AGGIORNATO");
            if (rs.next()){
                    UserBean retrievedUser = new UserBean();
                    retrievedUser.setId(rs.getInt("id"));
                    retrievedUser.setName(rs.getString("nome"));
                    retrievedUser.setSurname(rs.getString("cognome"));
                    retrievedUser.seteMail(rs.getString("eMail"));
                    retrievedUser.setPassword(rs.getString("passkey"));
                    retrievedUser.setPhoneNumber(rs.getString("telefono"));
                    retrievedUser.setBirthDate(rs.getDate("data_di_nascita").toString());
                    retrievedUser.setNation(rs.getString("nazione"));
                    retrievedUser.setAdmin(rs.getString("isAdmin").equals("true"));

                    return retrievedUser;
            }
            return null;
    }
}
