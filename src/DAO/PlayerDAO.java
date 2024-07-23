package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {

    public void addPlayer(String nickname)throws SQLException{
        if(!nameAlreadyUsed(nickname)){
            String query = "INSERT INTO players (nickname) VALUES (?)";
            PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
            ps.setString(1,nickname);
            ps.executeUpdate();
        }
    }

    public void deletePlayer(int id) throws SQLException {
        String query = "DELETE FROM players WHERE id = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void deleteAllPlayers() throws SQLException{
        String query = "DELETE * FROM players";
        ManagerDAO.result(query);
    }

    public ResultSet getPlayerByNickname(String nickname) throws SQLException{
        String query = "SELECT * FROM players p WHERE LOWER(p.nickname) LIKE LOWER('"+nickname+"%')";
        return ManagerDAO.result(query);
    }

    public ResultSet getAllPlayers() throws SQLException{
        String query = "SELECT * FROM players";
        return ManagerDAO.result(query);
    }

    public void editName(int id, String newNickname)throws SQLException{
        if(!nameAlreadyUsed(newNickname)){
            String query = "UPDATE players SET nickname ='"+newNickname+"' WHERE id ="+id;
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
