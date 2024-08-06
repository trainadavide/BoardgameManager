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
    private BoardgameService boardgameService;

    private UserService userService;
    private User user;

    private ServiceManager(){
        CollectionDAO collectionDAO = new CollectionDAO();
        MatchPlayerDAO matchPlayerDAO = new MatchPlayerDAO();
        MatchDAO matchDAO = new MatchDAO();
        WishlistDAO wishlistDAO = new WishlistDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        BoardgameDAO boardgameDAO = new BoardgameDAO();
        UserDAO userDAO = new UserDAO();
        boardgameService = new BoardgameService(boardgameDAO);
        collectionService = new CollectionService(collectionDAO, boardgameService);
        playerService = new PlayerService(playerDAO);
        matchService = new MatchService(matchDAO, matchPlayerService, boardgameService, playerService);
        matchPlayerService = new MatchPlayerService(matchPlayerDAO, playerService);
        wishlistService = new WishlistService(wishlistDAO, boardgameService);
        userService = new UserService(userDAO,matchService,playerService,collectionService,wishlistService);

        //TODO set user on services
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

    public UserService getUserService() {
        return userService;
    }

    public BoardgameService getBoardgameService() {
        return boardgameService;
    }
}
