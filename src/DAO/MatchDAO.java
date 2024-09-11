package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MatchDAO {

    public void addMatch(int gameId, int[] playersId, int[] points, Date date, int duration, int userId) throws SQLException {
        String query = "INSERT INTO match (gameid, date, duration, userid) VALUES (? ,? ,?, ?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,gameId);
        ps.setDate(2, (java.sql.Date) date);
        ps.setInt(3, duration);
        ps.setInt(4,userId);
        ps.executeUpdate();

        int matchId;

        query = "SELECT max(matchid) as massimo FROM match";
        ps = ManagerDAO.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        matchId = rs.getInt("massimo");

        for (int i=0; i<playersId.length; i++){
            if(playersId[i] != 0) {
                query = "INSERT INTO match_players (matchid, playerid, score) VALUES (?, ?, ?)";
                ps = ManagerDAO.getConnection().prepareStatement(query);
                ps.setInt(1, matchId);
                ps.setInt(2, playersId[i]);
                ps.setInt(3, points[i]);
                ps.executeUpdate();
            }
        }
    }

    public void removeMatch(int matchId)throws SQLException{
        String query = "DELETE FROM match WHERE matchid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, matchId);
        ps.executeUpdate();
        //match_players rows will be deleted on cascade
    }

    public ResultSet getMatches(int userId)throws SQLException{
        String query = "SELECT * FROM match WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, userId);
        return ps.executeQuery();
    }

    public ResultSet getMatchesByGame(int gameId,int userId)throws SQLException{
        String query = "SELECT * FROM match WHERE gameid = ? AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,gameId);
        ps.setInt(2, userId);
        return ps.executeQuery();
    }

    public int mostRecentlyAdded(int userId)throws SQLException{
        String query = "SELECT max(matchid) as massimo FROM match WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("massimo");
    }
}
