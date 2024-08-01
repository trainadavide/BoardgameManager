package BusinessLogic.Service;

import DAO.ManagerDAO;
import DAO.MatchDAO;
import Model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

//TODO implement entity modification in service
public class MatchService {
    private MatchDAO matchDAO;

    private User user;

    public MatchService(MatchDAO matchDAO){
        this.matchDAO = matchDAO;
    }

    public void setUser(User user){
        this.user=user;
    }

    public void addMatch(int gameId, int[] playersId, int[] points, Date date, int duration){
        try {
            matchDAO.addMatch(gameId, playersId, points, date, duration, user.getId());
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
            return matchDAO.getMatches(user.getId());
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: "+e.getMessage());
        }
        return null;
    }

    public ResultSet getMatchesByGame(int gameId){
        try {
            return matchDAO.getMatchesByGame(gameId, user.getId());
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: "+e.getMessage());
        }
        return null;
    }

}
