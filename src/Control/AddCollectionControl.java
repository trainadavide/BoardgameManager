package Control;

import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;

public class AddCollectionControl {
    public static boolean controlCollection(int id){
        ResultSet resultSet = Database.result("SELECT id FROM collection WHERE id = " + id);
        try {
            resultSet.absolute(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(resultSet.next()){
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static boolean controlWishlist(int id){
        return true;
    }
}
