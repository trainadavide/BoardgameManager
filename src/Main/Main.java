package Main;

import View.*;

import java.util.ArrayList;
import Model.*;

public class Main {
    public static ArrayList<Boardgame> collection = new ArrayList<>();
    public static ArrayList<Boardgame> wishlist = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();


    public static void main(String[] args) {
        collection = Control.LoadData.loadCollection();
        wishlist = Control.LoadData.loadWishlist();
        players = Control.LoadData.loadPlayers();

        MainWindow mainWindow = new MainWindow();
    }
}