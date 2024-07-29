package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO Check query post aggiunta user

public class WishlistDAO {

    public void addBoardGame(int id) throws SQLException {
        if (!alreadyInWishlist(id)){
            String query = "INSERT INTO wishlist (id) VALUES (?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }

    public void deleteBoardGame(int id) throws SQLException {
        String query = "DELETE FROM wishlist WHERE id = ?";
        PreparedStatement pstmt = ManagerDAO.getConnection().prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public void deleteAllWishlist() throws SQLException{
        String query = "DELETE * FROM wishlist";
        ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByName(String name) throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE LOWER(b.name) LIKE LOWER('"+name+"%')";
        return ManagerDAO.result(query);
    }

    public ResultSet getAllWishlist() throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id";
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMaxDuration(int maxDuration)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.avgDuration <="+maxDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMinDuration(int minDuration)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.avgDuration >="+minDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByPlayers(int players)throws SQLException{
        String query = "SELECT * wishlist w JOIN boardgame b ON w.id = b.id WHERE b.minPlayers <="+players+" AND b.maxPlayers >="+players;
        return ManagerDAO.result(query);
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive)throws SQLException{
        String query = "SELECT * FROM wishlist w JOIN boardgame b ON w.id = b.id WHERE b.competitive ="+competitive;
        return ManagerDAO.result(query);
    }

    private boolean alreadyInWishlist(int id) throws SQLException {
        boolean inCollection = false;
        String query = "SELECT * FROM wishlist WHERE id ="+id;
        if(ManagerDAO.select(query).next())
            inCollection = true;
        return inCollection;
    }
}