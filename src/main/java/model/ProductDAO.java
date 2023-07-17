package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                "' WHERE codice = '" + product.getCode() + "'";
        Statement stm = connection.createStatement();
        stm.executeUpdate(query);

        ConnectionPool.closeConnection();
    }

    public List<ProductBean> doRetrieveAll() throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Prodotto");

        ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            ProductBean tmpProduct = new ProductBean();
            tmpProduct.setCode(rs.getString("codice"));
            tmpProduct.setName(rs.getString("nome"));
            tmpProduct.setPrice(rs.getDouble("prezzo"));
            tmpProduct.setSale(rs.getInt("sconto"));
            tmpProduct.setCategory(rs.getString("categoria"));
            tmpProduct.setDescription(rs.getString("descrizione"));
            tmpProduct.setImage(rs.getString("immagine"));

            productList.add(tmpProduct);
        }

        return productList;
    }

    public List<ProductBean> doRetrieveByCategory(String category) throws SQLException{
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Prodotto " +
                        "WHERE categoria=?");
        ps.setString(1, category);
        ResultSet rs = ps.executeQuery();
        List<ProductBean> productList = new ArrayList<>();

        while (rs.next()){
            ProductBean tmpProduct = new ProductBean();
            tmpProduct.setCode(rs.getString("codice"));
            tmpProduct.setName(rs.getString("nome"));
            tmpProduct.setPrice(rs.getDouble("prezzo"));
            tmpProduct.setSale(rs.getInt("sconto"));
            tmpProduct.setCategory(rs.getString("categoria"));
            tmpProduct.setDescription(rs.getString("descrizione"));
            tmpProduct.setImage(rs.getString("immagine"));

            productList.add(tmpProduct);
        }
        return productList;
    }

    public ProductBean doRetrieveById(String code) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT * " +
                        "FROM Prodotto " +
                        "WHERE codice=?");
        ps.setString(1, code);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            ProductBean tmpProduct = new ProductBean();
            tmpProduct.setCode(rs.getString("codice"));
            tmpProduct.setName(rs.getString("nome"));
            tmpProduct.setPrice(rs.getDouble("prezzo"));
            tmpProduct.setSale(rs.getInt("sconto"));
            tmpProduct.setCategory(rs.getString("categoria"));
            tmpProduct.setDescription(rs.getString("descrizione"));
            tmpProduct.setImage(rs.getString("immagine"));

            return tmpProduct;
        }
        return null;
    }

    public List<String> doRetrieveCategories() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT DISTINCT categoria " +
                        "FROM Prodotto ");

        ResultSet rs = ps.executeQuery();
        List<String> categories = new ArrayList<>();

        while (rs.next()){
            String tmpCategory = rs.getString("categoria");
            categories.add(tmpCategory);
        }
        return categories;
    }
}
