package BusinessLogic.Service;

import DAO.ManagerDAO;
import DAO.MatchDAO;
import DAO.MatchPlayerDAO;
import Model.Boardgame;
import Model.Match;
import Model.Player;
import Model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MatchService {
    private MatchDAO matchDAO;
    private MatchPlayerService matchPlayerService;

    private BoardgameService boardgameService;

    private PlayerService playerService;

    private User user;

    public MatchService(MatchDAO matchDAO,MatchPlayerService matchPlayerService, BoardgameService boardgameService, PlayerService playerService){
        this.matchDAO = matchDAO;
        this.matchPlayerService = matchPlayerService;
        this.boardgameService = boardgameService;
        this.playerService = playerService;
    }

    public void setUser(User user){
        this.user=user;
    }

    public void addMatch(int gameId, int[] playersId, int[] points, Date date, int duration){
        try {
            matchDAO.addMatch(gameId, playersId, points, date, duration, user.getId());
            int matchId = matchDAO.mostRecentlyAdded(user.getId());
            Boardgame bg = boardgameService.createBoardgameFromId(gameId);
            Match m = new Match(matchId, bg);
            Player p;
            for(int i=0; i<playersId.length; i++){
                p = new Player(playersId[i], playerService.getNicknameById(playersId[i]));
                m.addPlayer(p,points[i]);
            }
            user.getMatchLog().addMatch(m);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del match: "+e.getMessage());
        }
    }

    public void removeMatch(int matchId){
        try {
            matchDAO.removeMatch(matchId);
            user.getMatchLog().removeMatch(matchId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione del match: "+e.getMessage());
        }
    }

    public ResultSet getMatches(){
        try {
            ResultSet rs = matchDAO.getMatches(user.getId());
            ArrayList<Match> matches = new ArrayList<>();
            Match m;
            Boardgame bg;
            ResultSet matchDetails;
            while (rs.next()){
                bg = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                m = new Match(rs.getInt("matchid"), bg);
                matchDetails = matchPlayerService.getMatchDetailsById(rs.getInt("matchid"));
                Player p;
                int playerId;
                while (matchDetails.next()){
                    playerId = matchDetails.getInt("playerid");
                    p = new Player(playerId, playerService.getNicknameById(playerId));
                    m.addPlayer(p,matchDetails.getInt("score"));
                }
                matches.add(m);
            }
            user.getMatchLog().loadMatches(matches);

            return rs;
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
