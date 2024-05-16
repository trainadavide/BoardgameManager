package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainWindow extends JFrame{
    public MainWindow(int x, int y) {
        JFrame w = this;
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Boardgame Manager");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(190,10,200,20);
        this.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel(new GridLayout(7,1));
        buttons.setBounds(150, 50, 200, 200);

        JButton collection = new JButton();
        collection.setText("Collection");
        collection.setBounds(50,50,150,90);
        buttons.add(collection);

        JPanel empty1 = new JPanel();
        buttons.add(empty1);

        JButton wishlist = new JButton();
        wishlist.setText("Wishlist");
        wishlist.setBounds(350,50,150,90);
        buttons.add(wishlist);

        JPanel empty2 = new JPanel();
        buttons.add(empty2);

        JButton players = new JButton();
        players.setText("Players");
        players.setBounds(200,50,150,90);
        buttons.add(players);

        JPanel empty3 = new JPanel();
        buttons.add(empty3);

        JButton match = new JButton();
        match.setText("Match");
        match.setBounds(200,125,150,90);
        buttons.add(match);

        this.add(buttons, BorderLayout.CENTER);


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
                    PlayersWindow playersWindow = new PlayersWindow(w.getLocation().x, w.getLocation().y, false,null,0);
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

        this.setBounds(x,y,500,320);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}