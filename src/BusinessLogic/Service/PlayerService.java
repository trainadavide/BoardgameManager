package BusinessLogic.Service;

import DAO.ManagerDAO;
import DAO.PlayerDAO;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO implement entity modification in service

public class PlayerService {
    private PlayerDAO playerDAO;
    private User user;

    public PlayerService(PlayerDAO playerDAO){
        this.playerDAO = playerDAO;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addPlayer(String nickname)throws SQLException {
        playerDAO.addPlayer(nickname, user.getId());
    }

    public void deletePlayer(int playerId){
        try {
            playerDAO.deletePlayer(playerId);
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del player: "+e.getMessage());
        }
    }

    public void deleteAllPlayers(){
        try {
            playerDAO.deleteAllPlayers(user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione dei player: "+e.getMessage());
        }
    }

    public ResultSet getPlayerByNickname(String nickname){
        try {
            return playerDAO.getPlayerByNickname(nickname, user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei player: "+e.getMessage());
        }
        return null;
    }

    public ResultSet getAllPlayers(){
        try {
            return playerDAO.getAllPlayers(user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei player: "+e.getMessage());
        }
        return null;
    }

    public void editPlayerName(int playerId, String newNickname){
        try {
            playerDAO.editName(playerId, newNickname, user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica del player: "+e.getMessage());
        }
    }
}
