package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {
    public void doSave(CartItemBean cartItem) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Item_Carrello (id_carrello, id_briefing, cod_prodotto, quantita) " +
                        "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, cartItem.getCartId());
        if (cartItem.getBriefingId() == -1)
            ps.setNull(2, Types.INTEGER);
        else
            ps.setInt(2, cartItem.getBriefingId());
        ps.setString(3, cartItem.getProductCode());
        ps.setInt(4, cartItem.getQuantity());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("INSERT CARTITEM error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        cartItem.setId(id);
        ConnectionPool.closeConnection();
    }

    public void doDeleteById(int id) throws SQLException {
        Connection con = ConnectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM Item_Carrello WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }

    public CartItemBean doRetrieveById(int cartItemId) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Item_Carrello " +
                        "WHERE id=?");
        ps.setInt(1, cartItemId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            CartItemBean cartItem = new CartItemBean();
            cartItem.setId(rs.getInt("id"));
            int briefID = rs.getInt("id_briefing")==0 ? -1 : rs.getInt("id_briefing");
            cartItem.setBriefingId(briefID);
            cartItem.setCartId(rs.getInt("id_carrello"));
            cartItem.setProductCode(rs.getString("cod_prodotto"));
            cartItem.setQuantity(rs.getInt("quantita"));
            return cartItem;
        }
        return null;
    }

    public List<CartItemBean> doRetrieveByCartId(int cartId) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Item_Carrello " +
                        "WHERE id_carrello=?");
        ps.setInt(1, cartId);
        ResultSet rs = ps.executeQuery();
        List<CartItemBean> cartItemList = new ArrayList<>();
        while (rs.next()){
            CartItemBean tmpItem = new CartItemBean();
            tmpItem.setId(rs.getInt("id"));
            int briefID = rs.getInt("id_briefing")==0 ? -1 : rs.getInt("id_briefing");
            tmpItem.setBriefingId(briefID);
            tmpItem.setCartId(rs.getInt("id_carrello"));
            tmpItem.setProductCode(rs.getString("cod_prodotto"));
            tmpItem.setQuantity(rs.getInt("quantita"));

            cartItemList.add(tmpItem);
        }
        return cartItemList;
    }

    public void updateCartId(CartItemBean cartItem, int newCartId) throws SQLException{
        Connection con = ConnectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Item_Carrello SET id_carrello = ? WHERE id = ?");
        ps.setInt(1, newCartId);
        ps.setInt(2, cartItem.getId());
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }
}
