package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardgameDAO {
    public ResultSet getAllBoardgames() throws SQLException{
        String query = "SELECT * FROM boardgame";
        return ManagerDAO.result(query);
    }
    public ResultSet getBoardGameByName(String name) throws SQLException {
        String query = "SELECT * FROM boardgame b WHERE LOWER(b.name) LIKE LOWER('"+name+"%')";
        return ManagerDAO.result(query);
    }
    public ResultSet getBoardGameByMaxDuration(int maxDuration)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE avgDuration <="+maxDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByMinDuration(int minDuration)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE avgDuration >="+minDuration;
        return ManagerDAO.result(query);
    }

    public ResultSet getBoardGameByPlayers(int players)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE minPlayers <="+players+" AND maxPlayers >="+players;
        return ManagerDAO.result(query);
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive)throws SQLException{
        String query = "SELECT * FROM boardgame b WHERE competitive ="+competitive;
        return ManagerDAO.result(query);
    }
}
