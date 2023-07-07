package model;

import java.sql.*;

public class ProductDAO {
    public void doSave(ProductBean newProduct) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Prodotto (codice, nome, prezzo, sconto, categoria, descrizione, immagine) " +
                        "VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, newProduct.getCode());
        ps.setString(2, newProduct.getName());
        ps.setDouble(3, newProduct.getPrice());
        ps.setInt(4, newProduct.getSale());
        ps.setString(5, newProduct.getCategory());
        ps.setString(6, newProduct.getDescription());
        ps.setString(7, newProduct.getImage());
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }

    public boolean isAlreadyRegistered(String code) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Prodotto WHERE codice=?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }


    public void doDeleteById(String id) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM Prodotto WHERE codice=?");
        ps.setString(1, id);
        ps.executeUpdate();
        ConnectionPool.closeConnection();
    }

    public void doUpdate(ProductBean product) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        String query = "UPDATE Prodotto SET codice = '" + product.getCode() + "', nome = '" + product.getName() + "', prezzo = '" + product.getPrice() + "', sconto = '" + product.getSale() + "', categoria = '" + product.getCategory() + "', descrizione = '" + product.getDescription() +  "', immagine = '" + product.getImage() +
                "'WHERE codice = " + product.getCode();
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);

        ConnectionPool.closeConnection();
    }
}
