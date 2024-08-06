package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistDAO {

    public void addBoardGame(int id, int userId) throws SQLException {
        if (!alreadyInWishlist(id, userId)){
            String query = "INSERT INTO wishlist (gameid, userid) VALUES (?,?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1,id);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
    }

    public void deleteBoardGame(int id, int userId) throws SQLException {
        String query = "DELETE FROM wishlist WHERE gameid = ? AND userid = ?";
        PreparedStatement pstmt = ManagerDAO.getConnection().prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.setInt(2, userId);
        pstmt.executeUpdate();
    }

    public void deleteAllWishlist(int userId) throws SQLException{
        String query = "DELETE * FROM wishlist WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,userId);
        ps.executeUpdate();
    }

    public ResultSet getBoardGameByName(String name, int userId) throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.gameid = b.id WHERE LOWER(b.name) LIKE LOWER(?'%') AND" +
                " userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,name);
        ps.setInt(2,userId);
        return ps.executeQuery();
    }

    public ResultSet getAllWishlist(int userId) throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.gameid = b.id WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, userId);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameByMaxDuration(int maxDuration, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.gameid = b.id WHERE b.avgDuration <= ?"+
                " AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,maxDuration);
        ps.setInt(2, userId);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameByMinDuration(int minDuration, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.gameid = b.id WHERE b.avgDuration >= ?"+
                " AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,minDuration);
        ps.setInt(2, userId);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameByPlayers(int players, int userId)throws SQLException{
        String query = "SELECT * wishlist w JOIN boardgame b ON w.gameid = b.id WHERE b.minPlayers <= ? AND b.maxPlayers >= ?"+
                " AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,players);
        ps.setInt(2,players);
        ps.setInt(3, userId);
        return ps.executeQuery();
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.gameid = b.id WHERE b.competitive = ?"+
                " AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setBoolean(1,competitive);
        ps.setInt(2, userId);
        return ps.executeQuery();
    }

    private boolean alreadyInWishlist(int id, int userId) throws SQLException {
        boolean inCollection = false;
        String query = "SELECT * FROM wishlist WHERE gameid = ? AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            inCollection = true;
        return inCollection;
    }
}