package BusinessLogic.Service;

import DAO.*;
import Model.Player;
import Model.User;

public class ServiceManager{
    private static ServiceManager instance;
    private CollectionService collectionService;
    private MatchPlayerService matchPlayerService;
    private MatchService matchService;
    private PlayerService playerService;
    private WishlistService wishlistService;
    private User user;

    private ServiceManager(){
        CollectionDAO collectionDAO = new CollectionDAO();
        MatchPlayerDAO matchPlayerDAO = new MatchPlayerDAO();
        MatchDAO matchDAO = new MatchDAO();
        WishlistDAO wishlistDAO = new WishlistDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        collectionService = new CollectionService(collectionDAO);
        matchPlayerService = new MatchPlayerService(matchPlayerDAO);
        matchService = new MatchService(matchDAO);
        playerService = new PlayerService(playerDAO);
        wishlistService = new WishlistService(wishlistDAO);
        //TODO add userservice and userDao
    }

    public static ServiceManager getInstance() {
        if (instance == null)
            instance = new ServiceManager();
        return instance;
    }

    public CollectionService getCollectionService() {
        return collectionService;
    }

    public MatchPlayerService getMatchPlayerService() {
        return matchPlayerService;
    }

    public MatchService getMatchService() {
        return matchService;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public WishlistService getWishlistService() {
        return wishlistService;
    }
}
