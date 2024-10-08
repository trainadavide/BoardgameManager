package BusinessLogic.Service;

import DAO.MatchDAO;
import Model.Boardgame;
import Model.Match;
import Model.Player;
import Model.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class  MatchService {
    private MatchDAO matchDAO;
    private MatchPlayerService matchPlayerService;

    private BoardgameService boardgameService;

    private PlayerService playerService;

    private User user;

    public MatchService(MatchDAO matchDAO, MatchPlayerService matchPlayerService, BoardgameService boardgameService, PlayerService playerService) {
        this.matchDAO = matchDAO;
        this.matchPlayerService = matchPlayerService;
        this.boardgameService = boardgameService;
        this.playerService = playerService;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean addMatch(int gameId, int[] playersId, int[] points, Date date, int duration) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            matchDAO.addMatch(gameId, playersId, points, sqlDate, duration, user.getId());
            int matchId = matchDAO.mostRecentlyAdded(user.getId());
            Boardgame bg = boardgameService.createBoardgameFromId(gameId);
            Match m = new Match(matchId, bg);
            m.setDate(sqlDate);
            m.setDuration(duration);
            Player p;
            for (int i = 0; i < playersId.length; i++) {
                if(playersId[i]!=0) {
                    p = new Player(playersId[i], playerService.getNicknameById(playersId[i]));
                    m.addPlayer(p, points[i]);
                }
            }
            user.getMatchLog().addMatch(m);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta del match: " + e.getMessage());
            return false;
        }
    }

    public boolean removeMatch(int matchId) {
        try {
            matchDAO.removeMatch(matchId);
            user.getMatchLog().removeMatch(matchId);
            return true;
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione del match: " + e.getMessage());
            return false;
        }
    }

    public ResultSet getMatches() {
        try {
            ResultSet rs = matchDAO.getMatches(user.getId());
            ArrayList<Match> matches = new ArrayList<>();
            Match m;
            Boardgame bg;
            ResultSet matchDetails;
            int matchId, playerId;
            while (rs.next()) {
                bg = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                matchId = rs.getInt("matchid");
                m = new Match(matchId, bg);
                m.setDate(rs.getDate("date"));
                m.setDuration(rs.getInt("duration"));
                matchDetails = matchPlayerService.getMatchDetailsById(matchId);
                Player p;
                while (matchDetails.next()) {
                    playerId = matchDetails.getInt("playerid");
                    p = new Player(playerId, playerService.getNicknameById(playerId));
                    m.addPlayer(p, matchDetails.getInt("score"));
                }

                matches.add(m);
            }
            user.getMatchLog().loadMatches(matches);

            return rs;
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Match> getMatchesByGame(int gameId) {
        try {
            ResultSet rs = matchDAO.getMatchesByGame(gameId, user.getId());
            ArrayList<Match> matches = new ArrayList<>();
            Match m;
            Boardgame bg;
            ResultSet matchDetails;
            while (rs.next()) {
                bg = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                m = new Match(rs.getInt("matchid"), bg);
                matchDetails = matchPlayerService.getMatchDetailsById(rs.getInt("matchid"));
                Player p;
                int playerId;
                while (matchDetails.next()) {
                    playerId = matchDetails.getInt("playerid");
                    p = new Player(playerId, playerService.getNicknameById(playerId));
                    m.addPlayer(p, matchDetails.getInt("score"));
                }
                matches.add(m);
            }
            return matches;
        } catch (SQLException e) {
            System.err.println("Errore nella lettura dei match: " + e.getMessage());
        }
        return null;
    }
}