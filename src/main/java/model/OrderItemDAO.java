package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {


    public void doSave(OrderItemBean orderItem) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Item_Ordine (numero_ordine, cod_prodotto, id_briefing, quantita, prezzo_acquisto) " +
                        "VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, orderItem.getOrderNumber());
        ps.setString(2, orderItem.getProductCode());
        if (orderItem.getBriefingId() == -1)
            ps.setNull(3, Types.INTEGER);
        else
            ps.setInt(3, orderItem.getBriefingId());
        ps.setInt(4, orderItem.getQuantity());
        ps.setDouble(5, orderItem.getPrice());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("INSERT ORDERITEM error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        orderItem.setId(id);
        ConnectionPool.closeConnection();
    }

    public List<OrderItemBean> doRetrieveByOrderId(int orderId) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Item_Ordine " +
                        "WHERE numero_ordine=?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        List<OrderItemBean> orderItemList = new ArrayList<>();
        while (rs.next()){
            OrderItemBean orderItem = new OrderItemBean();
            orderItem.setId(rs.getInt("id"));
            orderItem.setOrderNumber(rs.getInt("numero_ordine"));
            int briefID = rs.getInt("id_briefing")==0 ? -1 : rs.getInt("id_briefing");
            orderItem.setBriefingId(briefID);
            orderItem.setQuantity(rs.getInt("quantita"));
            orderItem.setPrice(rs.getDouble("prezzo_acquisto"));

            orderItemList.add(orderItem);
        }
        return  orderItemList;
    }
}
