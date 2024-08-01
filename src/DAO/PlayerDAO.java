package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    public void addPlayer(String nickname, int userId)throws SQLException{
        if(!nameAlreadyUsed(nickname)){
            String query = "INSERT INTO players (nickname, userid) VALUES (?, ?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,nickname);
            ps.setInt(2,userId);
            ps.executeUpdate();
        }
    }

    public void deletePlayer(int id) throws SQLException {
        String query = "DELETE FROM players WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void deleteAllPlayers(int userId) throws SQLException{
        String query = "DELETE * FROM players WHERE userid="+userId;
        ManagerDAO.result(query);
    }

    public ResultSet getPlayerByNickname(String nickname, int userId) throws SQLException{
        String query = "SELECT * FROM players p WHERE LOWER(p.nickname) LIKE LOWER('"+nickname+"%')"+
                " AND userid ="+userId;
        return ManagerDAO.result(query);
    }

    public ResultSet getAllPlayers(int userId) throws SQLException{
        String query = "SELECT * FROM players WHERE userid ="+userId;
        return ManagerDAO.result(query);
    }

    public void editName(int id, String newNickname, int userId)throws SQLException{
        if(!nameAlreadyUsed(newNickname)){
            String query = "UPDATE players SET nickname ='"+newNickname+"' WHERE id ="+id+
                    " AND userid="+userId;
            ManagerDAO.result(query);
        }
    }

    private boolean nameAlreadyUsed(String nickname)throws SQLException {
        boolean alreadyUsed = false;
        String query = "SELECT * FROM players WHERE nickname ="+nickname;
        if(ManagerDAO.select(query).next())
            alreadyUsed = true;
        return alreadyUsed;
    }
}
