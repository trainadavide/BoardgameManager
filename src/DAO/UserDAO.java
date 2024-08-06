package DAO;

import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public User selectUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
            user = new User(rs.getInt("id"), email,
                    rs.getString("password"), rs.getString("username"));
        }
        return user;
    }

    public boolean checkCredentials(String email, String password)throws SQLException{
        String query ="SELECT count(*) FROM users WHERE email = ? AND password = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return rs.getInt(1)==1;
        }
        return false;
    }

    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id_user = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    public void updateUser(int id, String username, String email, String password) throws SQLException{
        String query = "UPDATE users SET username = ?, email = ?, psw = ? WHERE id_user = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,email);
        ps.setString(3,password);
        ps.setInt(4,id);
        ps.executeUpdate();
    }

    public void addUser(String username, String email, String password) throws SQLException{
        String query = "INSERT INTO users (username, email, psw) VALUES (?, ?, ?)";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,username);
        ps.setString(2,email);
        ps.setString(3,password);
        ps.executeUpdate();
    }

    public ResultSet getPasswordByEmail(String email)throws SQLException{
        String query = "SELECT password FROM users WHERE email = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public boolean checkUserName(String username) throws SQLException {
        String query="SELECT COUNT(*) FROM users WHERE username = ?";
        PreparedStatement ps = ManagerDAO.getConnection().prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) >= 1;
        }
        return false;
    }

}
