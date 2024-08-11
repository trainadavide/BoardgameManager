import BusinessLogic.Service.BoardgameService;
import BusinessLogic.Service.ServiceFactory;
import BusinessLogic.Service.UserService;
import DAO.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ServiceFactory sf;
        sf = ServiceFactory.getInstance();

        UserService us = (UserService) sf.getService(sf.USER_SERVICE);
        try {
            if(us.checkCredentials("admin@gmail.com","admin"))
                us.login("admin@gmail.com");
        } catch (SQLException e) {

        }
    }
}