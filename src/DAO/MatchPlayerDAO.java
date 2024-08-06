package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchPlayerDAO {
    public ResultSet getMatchDetailsById(int id)throws SQLException {
        String query = "SELECT playerid, score FROM match_players WHERE matchid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        return ps.executeQuery();
    }

    public void editScore(int match_id, int player_id, int updatedScore)throws SQLException{
        String query = "UPDATE match_players SET score = ? WHERE matchid = ? AND playerid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,updatedScore);
        ps.setInt(2,match_id);
        ps.setInt(3,player_id);
        ps.executeUpdate();
    }

    public ResultSet getWinners(int match_id)throws SQLException{
        String query ="SELECT mp.player, p.nickname FROM match_players mp JOIN players p ON mp.playerid = p.playerid " +
                "WHERE score = (SELECT max(score) FROM match_players WHERE matchid = ?)"+
                "AND matchid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,match_id);
        ps.setInt(2,match_id);
        return ps.executeQuery();
    }

    public void removePlayerFromMatch(int match_id, int player_id)throws SQLException{
        String query = "DELETE FROM match_players WHERE matchid = ?"+
                " AND playerid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,match_id);
        ps.setInt(2,player_id);
        ps.executeUpdate();
    }

    public void addPlayerToMatch(int match_id, int player_id, int score)throws SQLException{
        String query = "INSERT INTO match_players (matchid, playerid, score) VALUES (?, ?, ?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, match_id);
        ps.setInt(2, player_id);
        ps.setInt(3, score);
        ps.executeUpdate();
    }

}
