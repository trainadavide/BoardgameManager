package BusinessLogic.Service;

import DAO.BoardgameDAO;
import Model.Boardgame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardgameService {
    private BoardgameDAO boardgameDAO;

    private Boardgame gameSelected = null;

    public BoardgameService(BoardgameDAO boardgameDAO){
        this.boardgameDAO = boardgameDAO;
    }
    public Boardgame createBoardgameFromId(int id){
        try {
            ResultSet rs = boardgameDAO.getBoardGameById(id);
            rs.next();
            String name = rs.getString("name");
            int minP = rs.getInt("minPlayers");
            int maxP = rs.getInt("maxPlayers");
            int avgD = rs.getInt("avgDuration");
            boolean comp = rs.getBoolean("competitive");
            String url = rs.getString("image");
            Boardgame boardgame = new Boardgame(id,name,minP,maxP,avgD,comp,url);
            return boardgame;
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura del gioco: "+e.getMessage());
            return null;
        }
    }

    public ResultSet getAllBoardgame(){
        try {
            return boardgameDAO.getAllBoardgames();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBoardgameByName(String name){
        try {
            return boardgameDAO.getBoardGameByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBoardgameByMinDuration(int minDuration){
        try {
            return boardgameDAO.getBoardGameByMinDuration(minDuration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBoardgameByMaxDuration(int maxDuration){
        try {
            return boardgameDAO.getBoardGameByMaxDuration(maxDuration);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBoardgameByPlayers(int players){
        try {
            return boardgameDAO.getBoardGameByPlayers(players);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getBoardgameByCompetitive(boolean competitive){
        try {
            return boardgameDAO.getCompetitiveBoardGame(competitive);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boardgame getBoardgameById(int id){
        try {
            ResultSet rs = boardgameDAO.getBoardGameById(id);
            rs.next();
            String name = rs.getString("name");
            int minP = rs.getInt("minPlayers");
            int maxP = rs.getInt("maxPlayers");
            int avgD = rs.getInt("avgDuration");
            boolean comp = rs.getBoolean("competitive");
            String url = rs.getString("image");
            Boardgame boardgame = new Boardgame(id,name,minP,maxP,avgD,comp,url);
            return boardgame;
        } catch (SQLException e) {
            System.err.println("Errore durante la lettura del gioco: "+e.getMessage());
            return null;
        }
    }

    public void setGameSelected(Integer gameId){
        if(gameId==null)
            this.gameSelected = null;
        else
            this.gameSelected = this.getBoardgameById(gameId);
    }
    public Boardgame getGameSelected(){
        return gameSelected;
    }
}
