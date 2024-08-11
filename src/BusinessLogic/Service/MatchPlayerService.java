package BusinessLogic.Service;

import DAO.MatchPlayerDAO;
import Model.Player;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchPlayerService {
    private MatchPlayerDAO matchPlayerDAO;
    private PlayerService playerService;

    private User user;

    public MatchPlayerService(MatchPlayerDAO matchPlayerDAO, PlayerService playerService){
        this.matchPlayerDAO = matchPlayerDAO;
        this.playerService = playerService;
    }

    public void setUser(User user){
        this.user = user;
    }

    public ResultSet getMatchDetailsById(int matchId){
        try {
            return matchPlayerDAO.getMatchDetailsById(matchId);
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei match: "+e.getMessage());
        }
        return null;
    }

    public boolean editScore(int matchId, int playerId, int updatedScore){
        try {
            matchPlayerDAO.editScore(matchId, playerId, updatedScore);
            user.getMatchLog().getMatchById(matchId).editScore(playerId, updatedScore);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica del punteggio: "+e.getMessage());
            return false;
        }
    }

    public ArrayList<Player> getWinners(int matchId){
        try {
            ResultSet rs = matchPlayerDAO.getWinners(matchId);
            ArrayList<Player> winners = new ArrayList<>();
            while (rs.next()){
                Player winner = new Player(rs.getInt(1),rs.getString(2));
                winners.add(winner);
            }
            return winners;
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei match: "+e.getMessage());
        }
        return null;
    }

    public boolean removePlayerFromMatch(int matchId, int playerId){
        try {
            matchPlayerDAO.removePlayerFromMatch(matchId, playerId);
            user.getMatchLog().getMatchById(matchId).removePlayer(playerId);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del player dal match: "+e.getMessage());
            return false;
        }
    }

    public boolean addPlayerToMatch(int matchId, int playerId, int score){
        try {
            matchPlayerDAO.addPlayerToMatch(matchId, playerId, score);
            Player p = new Player(playerId, playerService.getNicknameById(playerId));
            user.getMatchLog().getMatchById(matchId).addPlayer(p,score);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del player al match: "+e.getMessage());
            return false;
        }
    }
}
