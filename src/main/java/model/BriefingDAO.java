package model;

import java.sql.*;

public class BriefingDAO {
    public void doSave(BriefingBean briefing) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Briefing (id_utente, cod_prodotto, target, stile, obiettivi, palette_colori, note) " +
                        "VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        if (briefing.getUserId() == -1)
            ps.setNull(1, Types.INTEGER);
        else
            ps.setInt(1, briefing.getUserId());
        ps.setString(2, briefing.getProductCode());
        ps.setString(3, briefing.getTarget());
        ps.setString(4, briefing.getStyle());
        ps.setString(5, briefing.getGoals());
        ps.setString(6, briefing.getColorPalette());
        ps.setString(7, briefing.getNote());
        if (ps.executeUpdate() != 1) {
            throw new RuntimeException("INSERT BRIEFING error.");
        }
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int id = rs.getInt(1);
        briefing.setId(id);
        ConnectionPool.closeConnection();
    }

    public BriefingBean doRetrieveById(int briefingId) throws SQLException{
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Briefing " +
                            "WHERE id=?");
            ps.setInt(1, briefingId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                BriefingBean briefing = new BriefingBean();
                briefing.setId(rs.getInt("id"));
                briefing.setUserId(rs.getInt("id_utente"));
                briefing.setProductCode(rs.getString("cod_prodotto"));
                briefing.setTarget(rs.getString("target"));
                briefing.setStyle(rs.getString("stile"));
                briefing.setGoals(rs.getString("obiettivi"));
                briefing.setColorPalette(rs.getString("palette_colori"));
                briefing.setNote(rs.getString("note"));

                return briefing;
            }
            return null;
    }
}



