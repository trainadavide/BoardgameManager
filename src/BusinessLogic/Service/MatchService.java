package BusinessLogic.Service;

import DAO.ManagerDAO;
import DAO.MatchDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MatchService {
    private MatchDAO matchDAO;

    public MatchService(MatchDAO matchDAO){
        this.matchDAO = matchDAO;
    }

    public void addMatch(int gameId, int[] playersId, int[] points, Date date, int duration){
        try {
            matchDAO.addMatch(gameId, playersId, points, date, duration);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del match: "+e.getMessage());
        }
    }

    public void removeMatch(int matchId){
        try {
            matchDAO.removeMatch(matchId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione del match: "+e.getMessage());
        }
    }

    public ResultSet getMatches(){
        try {
            return matchDAO.getMatches();
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: "+e.getMessage());
        }
        return null;
    }

    public ResultSet getMatchesByGame(int gameId){
        try {
            return matchDAO.getMatchesByGame(gameId);
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: "+e.getMessage());
        }
        return null;
    }

}
