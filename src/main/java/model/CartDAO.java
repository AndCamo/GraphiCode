package model;

import java.sql.*;

public class CartDAO {

    public void doSave(CartBean newCart) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Carrello (id_utente, numero_articoli) " +
                        "VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, newCart.getUserId());
        ps.setInt(2, newCart.getProductCount());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("CART INSERT error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        newCart.setId(id);
        ConnectionPool.closeConnection();
    }

    public void doDelete(int userId) throws SQLException
    {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Carrello WHERE Utente=?");
        ps.setInt(1, userId);
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }

    public CartBean doRetrieveByUserID(int userId) throws  SQLException
    {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Carrello " +
                        "WHERE id_utente=?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            int cartId = rs.getInt("id");
            int number = rs.getInt("numero_articoli");
            CartBean newCart = new CartBean(cartId, userId);
            newCart.setProductCount(number);

            return newCart;
        }

        return null;
    }

    public void updateArticleNumber(CartBean cart, int newTotal) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Carrello SET numero_articoli = ? WHERE id = ?");
        ps.setInt(1, newTotal);
        ps.setInt(2, cart.getId());
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }
}
