package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import Database.Database;
import Control.AddCollectionControl;
import View.CreateMatchWindow;
import Main.Main;
import Control.LoadData;

public class AddGameWindow extends JFrame {
    private final int scope;

    public AddGameWindow(int scope, JFrame window) throws SQLException {

        this.scope = scope;
        ResultSet resultSet;

        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0, 0, 400, 600);

        if(scope==2){
            resultSet = Database.result("SELECT * FROM boardgame RIGHT JOIN collection c ON boardgame.id = c.id");
        }
        else {
            resultSet = Database.result("SELECT * FROM boardgame");
        }
        int i = 1;
        JPanel game;
        while (resultSet.next()) {
            game = new JPanel();
            game.setBounds(0, 0, 400, 200);
            game.setBorder(BorderFactory.createLineBorder(Color.black));
            resultSet.absolute(i);
            JLabel title = new JLabel(resultSet.getString("name"));
            title.setBounds(10, 0, 100, 100);
            JLabel minP = new JLabel("Giocatori: " + resultSet.getString("minplayers") + " - " + resultSet.getString("maxplayers"));
            minP.setBounds(10, 10, 100, 100);
            JLabel avgTime = new JLabel("Durata: " + resultSet.getString("avgduration"));
            avgTime.setBounds(100, 10, 100, 100);

            URL imageURL;
            try {
                imageURL = new URL(resultSet.getString("image"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ImageIcon img = new ImageIcon(imageURL);
            JLabel label = new JLabel(img);
            label.setBounds(0, 0, 100, 100);
            game.add(label);

            JButton add = new JButton("+");
            add.setBounds(600, 0, 50, 50);
            add.setName(resultSet.getString("id"));
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(AddCollectionControl.controlCollection(Integer.parseInt(add.getName()))&& scope==1){
                        Database.result("INSERT INTO collection (id) VALUES ("+ add.getName() + ")");
                        try {
                            Main.collection= LoadData.loadCollection();
                            JOptionPane.showMessageDialog(null, "Gioco aggiunto alla collezione");
                            window.dispose();
                            CollectionWindow collectionWindow = new CollectionWindow();
                        } catch (SQLException ex) {

                        }
                        dispose();
                    }
                    else if(AddCollectionControl.controlWishlist(Integer.parseInt(add.getName()))&& scope==0){
                        Database.result("INSERT INTO wishlist (id) VALUES ("+ add.getName() + ")");
                        try {
                            Main.wishlist= LoadData.loadWishlist();
                            JOptionPane.showMessageDialog(null, "Gioco aggiunto alla wishlist");
                            window.dispose();
                            WishlistWindow wishlistWindow = new WishlistWindow();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                    else if(scope==1){
                        JOptionPane.showMessageDialog(null, "Gioco già presente nella collezione");
                    }
                    else if(scope==0){
                        JOptionPane.showMessageDialog(null, "Gioco già presente nella wishlist");
                    }
                    else if(scope==2){
                        JOptionPane.showMessageDialog(null, "Selezionato");
                        try {
                            ((CreateMatchWindow) window).setGameID(Integer.parseInt(add.getName()));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        dispose();
                    }
                    }
            });

            game.add(title);
            game.add(minP);
            game.add(avgTime);
            game.add(add);
            panel.add(game);
            i++;


        }
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, 780, 680);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setSize(800, 700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
