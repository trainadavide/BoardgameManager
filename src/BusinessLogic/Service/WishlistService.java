package BusinessLogic.Service;

import DAO.CollectionDAO;
import DAO.WishlistDAO;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

//TODO implement entity modification in service

public class WishlistService {
    private WishlistDAO wishlistDAO;
    private User user;

    public WishlistService(WishlistDAO wishlistDAO){
        this.wishlistDAO = wishlistDAO;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addGameToWishlist(int gameId){
        try {
            wishlistDAO.addBoardGame(gameId, user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta alla collezione: "+e.getMessage());
        }
    }

    public void removeGameFromWishlist(int gameId){
        try {
            wishlistDAO.deleteBoardGame(gameId, user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione dalla collezione: "+e.getMessage());
        }
    }

    public void deleteWishlist(){
        try {
            wishlistDAO.deleteAllWishlist(user.getId());
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione della collezione: "+e.getMessage());
        }
    }

    public ResultSet getAllWishlist(){
        try {
            return wishlistDAO.getAllWishlist(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByName(String name){
        try {
            return wishlistDAO.getBoardGameByName(name, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByPlayer(int player){
        try {
            return wishlistDAO.getBoardGameByPlayers(player, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMinDuration(int minDuration){
        try {
            return wishlistDAO.getBoardGameByMinDuration(minDuration, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getBoardgamesByMaxDuration(int maxDuration){
        try {
            return wishlistDAO.getBoardGameByMaxDuration(maxDuration, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getCompetitiveBoardGame(boolean competitive){
        try {
            return wishlistDAO.getCompetitiveBoardGame(competitive, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
