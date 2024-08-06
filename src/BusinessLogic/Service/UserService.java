package BusinessLogic.Service;

import DAO.UserDAO;
import Model.Player;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    UserDAO userDAO;
    User user;

    MatchService matchService;
    PlayerService playerService;
    CollectionService collectionService;
    WishlistService wishlistService;


    public UserService(UserDAO userDAO, MatchService matchService, PlayerService playerService, CollectionService collectionService, WishlistService wishlistService) {
        this.userDAO = userDAO;
        this.matchService = matchService;
        this.playerService = playerService;
        this.collectionService = collectionService;
        this.wishlistService = wishlistService;
        user = new User();
    }

    public boolean checkCredentials(String email, String psw){
        try {
            boolean check =  userDAO.checkCredentials(email, psw);
            return check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void login(String email, String password) throws SQLException {
        user = userDAO.selectUserByEmail(email);

        collectionService.setUser(user);
        collectionService.getAllCollection();

        wishlistService.setUser(user);
        wishlistService.getAllWishlist();

        matchService.setUser(user);
        matchService.getMatches();

        playerService.setUser(user);
        playerService.getAllPlayers();

    }

    public User getCurrentUser(){
        return user;
    }

    public void register(String email, String password, String username) {
        try {
            userDAO.addUser(email, password, username);
        } catch (SQLException e) {

        }
    }

    public boolean checkUserName(String username) {
        try {
            return userDAO.checkUserName(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
