package BusinessLogic.Service;

import DAO.CollectionDAO;
import Model.Boardgame;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionService {
    private CollectionDAO collectionDAO;

    private BoardgameService boardgameService;

    private User user;

    public CollectionService(CollectionDAO collectionDAO, BoardgameService boardgameService){
        this.collectionDAO = collectionDAO;
        this.boardgameService = boardgameService;
    }

    public void setUser(User user){
        this.user = user;
    }
    public void addGameToCollection(int gameId){
        try {
            collectionDAO.addBoardGame(gameId, user.getId());
            Boardgame bg = boardgameService.createBoardgameFromId(gameId);
            user.getCollection().addToCollection(bg);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta alla collezione: "+e.getMessage());
        }
    }

    public void removeGameFromCollection(int gameId){
        try {
            collectionDAO.deleteBoardGame(gameId,user.getId());
            user.getCollection().removeFromCollection(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione dalla collezione: "+e.getMessage());
        }
    }

    public void deleteCollection(){
        try {
            collectionDAO.deleteAllCollection(user.getId());
            user.getCollection().deleteCollection();
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione della collezione: "+e.getMessage());
        }
    }

    public ResultSet getAllCollection(){
        try {
            ResultSet rs = collectionDAO.getAllCollection(user.getId());
            ArrayList<Boardgame> collection = new ArrayList<>();
            Boardgame bg;

            if (rs != null) {
                while (rs.next()){
                    bg = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                    collection.add(bg);
                }
                user.getCollection().loadCollection(collection);
            }

            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByName(String name){
        try {
            return collectionDAO.getBoardGameByName(name,user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByPlayer(int player){
        try {
            return collectionDAO.getBoardGameByPlayers(player,user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMinDuration(int minDuration){
        try {
            return collectionDAO.getBoardGameByMinDuration(minDuration,user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMaxDuration(int maxDuration){
        try {
            return collectionDAO.getBoardGameByMaxDuration(maxDuration,user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive){
        try {
            return collectionDAO.getCompetitiveBoardGame(competitive,user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
