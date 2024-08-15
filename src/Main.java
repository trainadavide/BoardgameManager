import BusinessLogic.Service.BoardgameService;
import BusinessLogic.Service.ServiceFactory;
import BusinessLogic.Service.UserService;
import DAO.*;
import View.*;
import Controller.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Engine engine = Engine.getInstance();

        Login loginView = new Login();
        loginView.setVisible(true);

    }
}
