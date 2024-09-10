package BusinessLogic.Service;

import DAO.ManagerDAO;
import DAO.PlayerDAO;
import Model.Boardgame;
import Model.Player;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerService {
    private PlayerDAO playerDAO;
    private User user;

    public PlayerService(PlayerDAO playerDAO){
        this.playerDAO = playerDAO;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addPlayer(String nickname) {
        try {
            playerDAO.addPlayer(nickname, user.getId());
            int newPlayerId = playerDAO.mostRecentlyAdded();
            Player p = new Player(newPlayerId, nickname);
            user.getFriends().addPlayer(p);
        }catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del player: "+e.getMessage());
        }
    }

    public void deletePlayer(int playerId){
        try {
            playerDAO.deletePlayer(playerId);
            user.getFriends().removeFromFriends(playerId);
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del player: "+e.getMessage());
        }
    }

    public void deleteAllPlayers(){
        try {
            playerDAO.deleteAllPlayers(user.getId());
            user.getFriends().deleteFriendsList();
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione dei player: "+e.getMessage());
        }
    }

    public int getPlayerIdByNickname(String nickname){
        try {
            ResultSet rs = playerDAO.getPlayerByNickname(nickname, user.getId());
            rs.next();
            return rs.getInt("playerid");
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei player: "+e.getMessage());
        }
        return 0;
    }

    public ResultSet getAllPlayers(){
        try {
            ResultSet rs = playerDAO.getAllPlayers(user.getId());
            ArrayList<Player> friends = new ArrayList<>();
            Player p;
            while (rs.next()){
                p = new Player(rs.getInt("playerid"),rs.getString("nickname"));
                friends.add(p);
            }
            user.getFriends().loadFriends(friends);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editPlayerName(int playerId, String newNickname){
        try {
            playerDAO.editName(playerId, newNickname, user.getId());
            user.getFriends().editNickname(playerId,newNickname);
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica del player: "+e.getMessage());
        }
    }

    public String getNicknameById(int playerId) {
        try {
            return playerDAO.getNicknameById(playerId);
        } catch (SQLException e) {
            System.err.println("Error getting nickname by id: " + e.getMessage());
        }
        return "ABBARABBA";
    }
}
