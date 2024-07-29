package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO Check query post aggiunta user

public class MatchPlayerDAO {
    public ResultSet getMatchDetailsById(int id)throws SQLException {
        String query = "SELECT id_player, points FROM match_players WHERE id_match = "+id;
        return ManagerDAO.result(query);
    }

    public void editScore(int match_id, int player_id, int updatedScore)throws SQLException{
        String query = "UPDATE match_players SET points = "+updatedScore+" WHERE id_match ="
                +match_id+" AND id_player ="+player_id;
        ManagerDAO.result(query);
    }

    public ResultSet getWinners(int match_id)throws SQLException{
        String query ="SELECT mp.id_player, p.nickname FROM match_players mp JOIN players p ON mp.id_player = p.id " +
                "WHERE points = (SELECT max(points) FROM match_players WHERE id_match ="+match_id+")"+
                "AND id_match ="+match_id;
        return ManagerDAO.result(query);
    }

    public void removePlayerFromMatch(int match_id, int player_id)throws SQLException{
        String query = "DELETE FROM match_players WHERE id_match ="+match_id+
                " AND id_player ="+player_id;
        ManagerDAO.result(query);
    }

    public void addPlayerToMatch(int match_id, int player_id, int score)throws SQLException{
        String query = "INSERT INTO match_players (id_match, id_player, points) VALUES (?, ?, ?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, match_id);
        ps.setInt(2, player_id);
        ps.setInt(3, score);
        ps.executeUpdate();
    }

}
