package DAO;

import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User selectUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = "+email;
        ResultSet rs = ManagerDAO.result(query);
        User user = null;
        if(rs.next()) {
            user = new User(rs.getInt("id"), email,
                    rs.getString("password"), rs.getString("username"));
        }
        return user;
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id_user = " + id;
        ManagerDAO.result(query);
    }

    public void updateUser(int id, String username, String email, String password) {
        String query = "UPDATE users SET username = '" + username + "', email = '" + email + "', psw = '" + password + "' WHERE id_user = " + id;
        ManagerDAO.result(query);
    }

    public void addUser(String username, String email, String password) {
        String query = "INSERT INTO users (username, email, psw) VALUES ('" + username + "', '" + email + "', '" + password + "')";
        ManagerDAO.result(query);
    }

    public ResultSet getPasswordByEmail(String email)throws SQLException{
        String query = "SELECT password FROM users WHERE email ="+email;
        ResultSet rs = ManagerDAO.result(query);
        return rs;
    }

}
