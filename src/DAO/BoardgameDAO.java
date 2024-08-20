package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardgameDAO {
    public ResultSet getAllBoardgames() throws SQLException{
        String query = "SELECT * FROM boardgame";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameById(int id) throws SQLException {
        String query = "SELECT * FROM boardgame b WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        return ps.executeQuery();
    }
    public ResultSet getBoardGameByName(String name) throws SQLException {
        String query = "SELECT * FROM boardgame b WHERE LOWER(b.name) LIKE LOWER(?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, name + "%");
        return ps.executeQuery();
    }
    public ResultSet getBoardGameByMaxDuration(int maxDuration)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE avgDuration <= ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, maxDuration);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameByMinDuration(int minDuration)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE avgDuration >= ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, minDuration);
        return ps.executeQuery();
    }

    public ResultSet getBoardGameByPlayers(int players)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE minPlayers <= ? AND maxPlayers >= ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, players);
        ps.setInt(2, players);
        return ps.executeQuery();
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE competitive = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setBoolean(1, competitive);
        return ps.executeQuery();
    }
}
