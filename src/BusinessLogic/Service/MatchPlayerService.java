package BusinessLogic.Service;

import DAO.MatchPlayerDAO;
import Model.Player;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void editScore(int matchId, int playerId, int updatedScore){
        try {
            matchPlayerDAO.editScore(matchId, playerId, updatedScore);
            user.getMatchLog().getMatchById(matchId).editScore(playerId, updatedScore);
        } catch (SQLException e) {
            System.err.println("Errore durante la modifica del punteggio: "+e.getMessage());
        }
    }

    public ResultSet getWinners(int matchId){
        try {
            return matchPlayerDAO.getWinners(matchId);
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura dei match: "+e.getMessage());
        }
        return null;
    }

    public void removePlayerFromMatch(int matchId, int playerId){
        try {
            matchPlayerDAO.removePlayerFromMatch(matchId, playerId);
            user.getMatchLog().getMatchById(matchId).removePlayer(playerId);
        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del player dal match: "+e.getMessage());
        }
    }

    public void addPlayerToMatch(int matchId, int playerId, int score){
        try {
            matchPlayerDAO.addPlayerToMatch(matchId, playerId, score);
            Player p = new Player(playerId, playerService.getNicknameById(playerId));
            user.getMatchLog().getMatchById(matchId).addPlayer(p,score);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del player al match: "+e.getMessage());
        }
    }
}
