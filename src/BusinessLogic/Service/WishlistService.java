package BusinessLogic.Service;

import DAO.CollectionDAO;
import DAO.WishlistDAO;
import Model.Boardgame;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class WishlistService {
    private WishlistDAO wishlistDAO;
    private BoardgameService boardgameService;
    private User user;

    public WishlistService(WishlistDAO wishlistDAO,BoardgameService boardgameService){
        this.wishlistDAO = wishlistDAO;
        this.boardgameService = boardgameService;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addGameToWishlist(int gameId){
        try {
            wishlistDAO.addBoardGame(gameId, user.getId());
            Boardgame bg = boardgameService.createBoardgameFromId(gameId);
            user.getWishlist().addToWishlist(bg);
        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiunta alla collezione: "+e.getMessage());
        }
    }

    public void removeGameFromWishlist(int gameId){
        try {
            wishlistDAO.deleteBoardGame(gameId, user.getId());
            user.getWishlist().removeFromWishlist(gameId);
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione dalla collezione: "+e.getMessage());
        }
    }

    public void deleteWishlist(){
        try {
            wishlistDAO.deleteAllWishlist(user.getId());
            user.getWishlist().deleteWishlist();
        } catch (SQLException e) {
            System.err.println("Errore durante la rimozione della collezione: "+e.getMessage());
        }
    }

    public ResultSet getAllWishlist(){
        try {
            ResultSet rs = wishlistDAO.getAllWishlist(user.getId());
            ArrayList<Boardgame> wishlist = new ArrayList<>();
            Boardgame bg;
            while (rs.next()){
                bg = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                wishlist.add(bg);
            }
            user.getWishlist().loadWishlist(wishlist);
            return rs;
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
