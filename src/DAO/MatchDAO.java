package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MatchDAO {

    public void addMatch(int gameId, int[] playersId, int[] points, Date date, int duration) throws SQLException {
        String query = "INSERT INTO match (game, date, duration) VALUES (? ,? ,?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,gameId);
        ps.setDate(2, (java.sql.Date) date);
        ps.setInt(3, duration);
        ps.executeUpdate();

        int matchId;

        query = "SELECT max(id) as massimo FROM match";
        ResultSet rs = ManagerDAO.result(query);
        rs.absolute(1);
        matchId = rs.getInt("massimo");

        for (int i=0; i<playersId.length; i++){
            query = "INSERT INTO match_players (id_match, id_player, points) VALUES (?, ?, ?)";
            ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setInt(1,matchId);
            ps.setInt(2, playersId[i]);
            ps.setInt(3, points[i]);
            ps.executeUpdate();
        }
    }

    public void removeMatch(int matchId)throws SQLException{
        String query = "DELETE FROM match WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, matchId);
        ps.executeUpdate();
        //match_players rows will be deleted on cascade
    }

    public ResultSet getMatches()throws SQLException{
        String query = "SELECT * FROM match";
        return ManagerDAO.result(query);
    }
}
