package Control;

import Model.*;
import Database.Database;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoadData {
    public static ArrayList<Boardgame> loadCollection() {
        ArrayList<Boardgame> collection = new ArrayList<>();
        ResultSet rs = Database.result("SELECT * FROM boardgame RIGHT JOIN collection ON boardgame.id = collection.id");
        int i = 0;
        try {
            rs.absolute(i);
            while (rs.next()) {
                collection.add(new Boardgame(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("minPlayers"), rs.getInt("maxPlayers"),
                        rs.getInt("avgDuration"), rs.getBoolean("competitive"), rs.getString("image")));
                i++;
                rs.absolute(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }

    public static ArrayList<Boardgame> loadWishlist() {
        ArrayList<Boardgame> wishlist = new ArrayList<>();
        ResultSet rs = Database.result("SELECT * FROM boardgame RIGHT JOIN wishlist ON boardgame.id = wishlist.id");
        int i = 0;
        try {
            rs.absolute(i);
            while (rs.next()) {
                wishlist.add(new Boardgame(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("minPlayers"), rs.getInt("maxPlayers"),
                        rs.getInt("avgDuration"), rs.getBoolean("competitive"), rs.getString("image")));
                i++;
                rs.absolute(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wishlist;
    }

    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        ResultSet rs = Database.result("SELECT * FROM player");
        int i = 0;
        try {
            rs.absolute(i);
            while (rs.next()) {
                players.add(new Player(rs.getInt("id"), rs.getString("nickname")));
                i++;
                rs.absolute(i);
            }
        } catch (Exception e) {
        }
        return players;
    }
}
