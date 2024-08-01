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
        String query = "DELETE * FROM wishlist WHERE userid ="+userId;
        ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByName(String name, int userId) throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE LOWER(b.name) LIKE LOWER('"+name+"%') AND" +
                " userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getAllWishlist(int userId) throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMaxDuration(int maxDuration, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.avgDuration <="+maxDuration+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMinDuration(int minDuration, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.avgDuration >="+minDuration+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByPlayers(int players, int userId)throws SQLException{
        String query = "SELECT * wishlist w JOIN boardgame b ON w.id = b.id WHERE b.minPlayers <="+players+" AND b.maxPlayers >="+players+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive, int userId)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.competitive ="+competitive+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    private boolean alreadyInWishlist(int id, int userId) throws SQLException {
        boolean inCollection = false;
        String query = "SELECT * FROM wishlist WHERE id ="+id+" AND userid = "+userId;
        if(ManagerDAO.select(query).next())
            inCollection = true;
        return inCollection;
    }
}