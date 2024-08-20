package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    public void addPlayer(String nickname, int userId)throws SQLException{
        if(!nameAlreadyUsed(nickname, userId)){
            String query = "INSERT INTO players (nickname, userid) VALUES (?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,nickname);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
    }

    public void deletePlayer(int id) throws SQLException {
        String query = "DELETE FROM players WHERE playerid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void deleteAllPlayers(int userId) throws SQLException{
        String query = "DELETE * FROM players WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,userId);
        ps.executeUpdate();
    }

    public ResultSet getPlayerByNickname(String nickname, int userId) throws SQLException{
        String query = "SELECT * FROM players p WHERE LOWER(p.nickname) LIKE LOWER(?)"+
                " AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,nickname + '%');
        ps.setInt(2,userId);
        return ps.executeQuery();
    }

    public ResultSet getAllPlayers(int userId) throws SQLException{
        String query = "SELECT * FROM players WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,userId);
        return ps.executeQuery();
    }

    public void editName(int id, String newNickname, int userId)throws SQLException{
        if(!nameAlreadyUsed(newNickname, userId)){
            String query = "UPDATE players SET nickname = ? WHERE playerid = ?"+
                    " AND userid = ?";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,newNickname);
            ps.setInt(2,id);
            ps.setInt(3,userId);
            ps.executeUpdate();
        }
    }

    private boolean nameAlreadyUsed(String nickname, int userId)throws SQLException {
        boolean alreadyUsed = false;
        String query = "SELECT * FROM players WHERE nickname = ? AND userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,nickname);
        ps.setInt(2,userId);
        if(ps.executeQuery().next())
            alreadyUsed = true;
        return alreadyUsed;
    }

    public int mostRecentlyAdded(int userId)throws SQLException{
        String query = "SELECT max(playerid) FROM players WHERE userid = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,userId);
        ResultSet rs = ps.executeQuery();
        return rs.getInt("playerid");
    }
}
