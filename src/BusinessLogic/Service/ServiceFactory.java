package BusinessLogic.Service;

public class ServiceFactory {
    private static ServiceFactory instance;
    public final int BOARDGAME_SERVICE = 1;
    public final int COLLECTION_SERVICE = 2;
    public final int WISHLIST_SERVICE = 3;
    public final int MATCH_SERVICE = 4;
    public final int MATCH_PLAYER_SERVICE = 5;
    public final int PLAYER_SERVICE = 6;
    public final int USER_SERVICE = 7;


    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        if (instance == null)
            instance = new ServiceFactory();
        return instance;
    }

    public Object getService(int service){
        switch (service){
            case BOARDGAME_SERVICE :
                return ServiceManager.getInstance().getBoardgameService();
            case COLLECTION_SERVICE :
                return ServiceManager.getInstance().getCollectionService();
            case WISHLIST_SERVICE :
                return ServiceManager.getInstance().getWishlistService();
            case MATCH_SERVICE :
                return ServiceManager.getInstance().getMatchService();
            case MATCH_PLAYER_SERVICE :
                return ServiceManager.getInstance().getMatchPlayerService();
            case PLAYER_SERVICE :
                return ServiceManager.getInstance().getPlayerService();
            case USER_SERVICE :
                return ServiceManager.getInstance().getUserService();
            default:
                throw new IllegalArgumentException("Invalid service");
        }
    }
}
