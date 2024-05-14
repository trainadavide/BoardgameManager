package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainWindow extends JFrame{
    public MainWindow(int x, int y) {
        JFrame w = this;
        JLabel title = new JLabel("Boardgame Manager");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(180,10,200,20);
        this.add(title);

        JButton collection = new JButton();
        collection.setText("Collection");
        collection.setBounds(50,50,100,50);
        this.add(collection);

        JButton players = new JButton();
        players.setText("Players");
        players.setBounds(200,50,100,50);
        this.add(players);

        JButton match = new JButton();
        match.setText("Match");
        match.setBounds(200,125,100,50);
        this.add(match);

        JButton wishlist = new JButton();
        wishlist.setText("Wishlist");
        wishlist.setBounds(350,50,100,50);
        this.add(wishlist);

        collection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    CollectionWindow collectionWindow = new CollectionWindow(w.getLocation().x, w.getLocation().y);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        players.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PlayersWindow playersWindow = new PlayersWindow(w.getLocation().x, w.getLocation().y);
                } catch (Exception exception) {
                }
            }

        });

        match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MatchWindow matchWindow = new MatchWindow(w.getLocation().x, w.getLocation().y);
                } catch (Exception exception) {
                }
            }

        });

        wishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    WishlistWindow WishlistWindow = new WishlistWindow(w.getLocation().x, w.getLocation().y);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });

        this.setBounds(x,y,500,250);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}