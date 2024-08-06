package BusinessLogic.Service;

import DAO.BoardgameDAO;
import Model.Boardgame;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardgameService {
    private BoardgameDAO boardgameDAO;

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
}
