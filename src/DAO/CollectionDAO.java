package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionDAO {

    public void addBoardGame(int id, int userId) throws SQLException {
        if (!alreadyInCollection(id,userId)){
            String query = "INSERT INTO collection (gameid, userid) VALUES (?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1,id);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
    }

    public void deleteBoardGame(int id, int userId) throws SQLException {
        String query = "DELETE FROM collection WHERE gameid = ? AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.setInt(2,userId);
        ps.executeUpdate();
    }

    public void deleteAllCollection(int userId) throws SQLException{
        String query = "DELETE * FROM collection WHERE userid ="+userId;
        ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByName(String name, int userId) throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE LOWER(b.name) LIKE LOWER('"+name+"%')"+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getAllCollection(int userId) throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMaxDuration(int maxDuration, int userId)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.avgDuration <="+maxDuration+
                " AND userid = "+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMinDuration(int minDuration, int userId)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.avgDuration >="+minDuration+
                " AND userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByPlayers(int players, int userId)throws SQLException{
        String query = "SELECT * collection c JOIN boardgame b ON c.id = b.id WHERE b.minPlayers <="+players+" AND b.maxPlayers >="+players+
                " AND userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive, int userId)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.competitive ="+competitive+
                " AND userid="+userId;
        return ManagerDAO.result(query);
    }

    private boolean alreadyInCollection(int id, int userId) throws SQLException {
        boolean inCollection = false;
        String query = "SELECT * FROM Collection WHERE id ="+id+" AND userid = "+userId;
        if(ManagerDAO.select(query).next())
            inCollection = true;
        return inCollection;
    }
}
