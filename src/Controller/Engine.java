package Controller;

import BusinessLogic.Service.ServiceFactory;
import Model.User;

public class Engine {
    //TODO
    private static Engine istance;
    private User user;
    private ServiceFactory sf;

    private Engine() {
        sf =  ServiceFactory.getInstance();
    }
    public User getUser() {
        return user;
    }
    public static Engine getInstance() {
        if(istance==null)
            istance = new Engine();
        return istance;
    }
}
