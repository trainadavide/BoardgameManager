package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionDAO {

    //TODO Check query post aggiunta user

    public void addBoardGame(int id) throws SQLException {
        if (!alreadyInCollection(id)){
            String query = "INSERT INTO collection (id) VALUES (?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
        }
    }

    public void deleteBoardGame(int id) throws SQLException {
        String query = "DELETE FROM collection WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void deleteAllCollection() throws SQLException{
        String query = "DELETE * FROM collection";
        ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByName(String name) throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE LOWER(b.name) LIKE LOWER('"+name+"%')";
        return ManagerDAO.result(query);
    }

    public ResultSet getAllCollection() throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id";
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMaxDuration(int maxDuration)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.avgDuration <="+maxDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMinDuration(int minDuration)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.avgDuration >="+minDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByPlayers(int players)throws SQLException{
        String query = "SELECT * collection c JOIN boardgame b ON c.id = b.id WHERE b.minPlayers <="+players+" AND b.maxPlayers >="+players;
        return ManagerDAO.result(query);
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive)throws SQLException{
        String query = "SELECT * FROM collection c JOIN boardgame b ON c.id = b.id WHERE b.competitive ="+competitive;
        return ManagerDAO.result(query);
    }

    private boolean alreadyInCollection(int id) throws SQLException {
        boolean inCollection = false;
        String query = "SELECT * FROM Collection WHERE id ="+id;
        if(ManagerDAO.select(query).next())
            inCollection = true;
        return inCollection;
    }
}
