package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void doUpdate(UserBean user) throws SQLException {
            Connection connection = ConnectionPool.getConnection();
            String query = "UPDATE Utente SET nome = '" + user.getName() + "', cognome = '" + user.getSurname() + "', eMail = '" + user.geteMail() + "', passkey = '" + user.getPassword() + "', telefono = '" + user.getPhoneNumber() + "', data_di_nascita = '" + user.getBirthDate() +  "', nazione = '" + user.getNation() +
                    "', isAdmin = '" + user.isAdmin() + "'WHERE id = " + user.getId();
            Statement stm = connection.createStatement();
            stm.executeUpdate(query);

            ConnectionPool.closeConnection();
    }

    public void changeAdminStatus(UserBean user, boolean isAdmin) throws SQLException{
            Connection connection = ConnectionPool.getConnection();
            String query = "UPDATE Utente SET isAdmin = '" + isAdmin + "'WHERE id = " + user.getId();
            Statement stm = connection.createStatement();
            stm.executeUpdate(query);

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

        public UserBean doRetrieveById(int userId) throws SQLException {
                Connection connection = ConnectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT * " +
                                "FROM Utente " +
                                "WHERE id=?");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
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

    public List<UserBean> doRetrieveAll() throws SQLException{
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM Utente");

            ArrayList<UserBean> usersList = new ArrayList<UserBean>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                    UserBean tmpUser = new UserBean();
                    tmpUser.setId(rs.getInt("id"));
                    tmpUser.setName(rs.getString("nome"));
                    tmpUser.setSurname(rs.getString("cognome"));
                    tmpUser.seteMail(rs.getString("eMail"));
                    tmpUser.setHashedPassword(rs.getString("passkey"));
                    tmpUser.setPhoneNumber(rs.getString("telefono"));
                    tmpUser.setBirthDate(rs.getDate("data_di_nascita").toString());
                    tmpUser.setNation(rs.getString("nazione"));
                    tmpUser.setAdmin(rs.getString("isAdmin").equals("true"));

                    usersList.add(tmpUser);
            }

            return usersList;
    }
}
