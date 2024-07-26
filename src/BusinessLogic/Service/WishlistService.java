package BusinessLogic.Service;

import DAO.CollectionDAO;
import DAO.WishlistDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistService {
    private WishlistDAO wishlistDAO;

    public WishlistService(WishlistDAO wishlistDAO){
        this.wishlistDAO = wishlistDAO;
    }

    public void addGameToWishlist(int gameId){
        try {
            wishlistDAO.addBoardGame(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta alla collezione: "+e.getMessage());
        }
    }

    public void removeGameFromWishlist(int gameId){
        try {
            wishlistDAO.deleteBoardGame(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione dalla collezione: "+e.getMessage());
        }
    }

    public void deleteWishlist(){
        try {
            wishlistDAO.deleteAllWishlist();
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione della collezione: "+e.getMessage());
        }
    }

    public ResultSet getAllWishlist(){
        try {
            return wishlistDAO.getAllWishlist();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByName(String name){
        try {
            return wishlistDAO.getBoardGameByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByPlayer(int player){
        try {
            return wishlistDAO.getBoardGameByPlayers(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMinDuration(int minDuration){
        try {
            return wishlistDAO.getBoardGameByMinDuration(minDuration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMaxDuration(int maxDuration){
        try {
            return wishlistDAO.getBoardGameByMaxDuration(maxDuration);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive){
        try {
            return wishlistDAO.getCompetitiveBoardGame(competitive);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
