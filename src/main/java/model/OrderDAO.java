package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public void doSave(OrderBean order) throws SQLException {
        Connection connection = ConnectionPool.getConnection();

        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Ordine (id_pagamento, id_utente, data_ordine, importo) " +
                        "VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getPaymentID());
        ps.setInt(2, order.getUserId());
        ps.setString(3, order.getOrderDate());
        ps.setDouble(4, order.getTotalAmount());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("INSERT ORDER error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        order.setOrderNumber(id);
        ConnectionPool.closeConnection();
    }

    public OrderBean doRetrieveById(int orderId) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * " +
                "FROM Ordine " +
                "WHERE numero_ordine=?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            int id = rs.getInt("numero_ordine");
            int paymentId = rs.getInt("id_pagamento");
            int userId = rs.getInt("id_utente");
            String date = rs.getDate("data_ordine").toString();
            double total = rs.getDouble("importo");

            OrderBean newOrder = new OrderBean(id, userId, paymentId, date, total);

            return newOrder;
        }

        return null;
    }

    public List<OrderBean> doRetrieveAll() throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * " +
                "FROM Ordine");
        ResultSet rs = ps.executeQuery();

        List<OrderBean> orderList = new ArrayList<>();

        while (rs.next()){
            OrderBean tmpOrder = new OrderBean();
            tmpOrder.setOrderNumber(rs.getInt("numero_ordine"));
            tmpOrder.setPaymentID(rs.getInt("id_pagamento"));
            tmpOrder.setUserId(rs.getInt("id_utente"));
            tmpOrder.setOrderDate(rs.getDate("data_ordine").toString());
            tmpOrder.setTotalAmount(rs.getDouble("importo"));

            orderList.add(tmpOrder);
        }

        return orderList;
    }

    public List<OrderBean> doRetrieveByUserId(int userId) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * " +
                "FROM Ordine " +
                "WHERE id_utente=?");

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        List<OrderBean> orderList = new ArrayList<>();

        while (rs.next()){
            OrderBean tmpOrder = new OrderBean();
            tmpOrder.setOrderNumber(rs.getInt("numero_ordine"));
            tmpOrder.setPaymentID(rs.getInt("id_pagamento"));
            tmpOrder.setUserId(rs.getInt("id_utente"));
            tmpOrder.setOrderDate(rs.getDate("data_ordine").toString());
            tmpOrder.setTotalAmount(rs.getDouble("importo"));

            orderList.add(tmpOrder);
        }

        return orderList;
    }

    public void doUpdateTotal(OrderBean order,double totalPrice) throws SQLException{
        Connection con = ConnectionPool.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "UPDATE Ordine SET importo = ? WHERE numero_ordine = ?");
        ps.setDouble(1, totalPrice);
        ps.setInt(2, order.getOrderNumber());
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }
}
