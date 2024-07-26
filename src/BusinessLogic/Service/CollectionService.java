package BusinessLogic.Service;

import DAO.CollectionDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionService {
    private CollectionDAO collectionDAO;

    public CollectionService(CollectionDAO collectionDAO){
        this.collectionDAO = collectionDAO;
    }

    public void addGameToCollection(int gameId){
        try {
            collectionDAO.addBoardGame(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta alla collezione: "+e.getMessage());
        }
    }

    public void removeGameFromCollection(int gameId){
        try {
            collectionDAO.deleteBoardGame(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione dalla collezione: "+e.getMessage());
        }
    }

    public void deleteCollection(){
        try {
            collectionDAO.deleteAllCollection();
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione della collezione: "+e.getMessage());
        }
    }

    public ResultSet getAllCollection(){
        try {
            return collectionDAO.getAllCollection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByName(String name){
        try {
            return collectionDAO.getBoardGameByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByPlayer(int player){
        try {
            return collectionDAO.getBoardGameByPlayers(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMinDuration(int minDuration){
        try {
            return collectionDAO.getBoardGameByMinDuration(minDuration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMaxDuration(int maxDuration){
        try {
            return collectionDAO.getBoardGameByMaxDuration(maxDuration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive){
        try {
            return collectionDAO.getCompetitiveBoardGame(competitive);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
