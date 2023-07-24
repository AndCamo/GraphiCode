package model;

import java.sql.*;

public class PaymentDAO {
    public void doSave(PaymentBean newPayment) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Pagamento (id_utente, numero_carta, titolare_carta, cvv, scadenza) " +
                        "VALUES(?,?,?,SHA1(?),?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, newPayment.getUserId());
        ps.setString(2, newPayment.getCardNumber());
        ps.setString(3, newPayment.getCardHorlder());
        ps.setString(4, newPayment.getCvv());
        ps.setString(5, newPayment.getExpirationDate());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("INSERT PAYMENT error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        newPayment.setId(id);

        ConnectionPool.closeConnection();
    }
}
