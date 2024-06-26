package View;

import Control.AddCollectionControl;
import Control.LoadData;
import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.Main;
import Model.Boardgame;

public class CollectionWindow extends JFrame {
    public CollectionWindow(int x, int y) throws SQLException {
        JFrame w = this;
        JLabel title = new JLabel("Collection");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel(new GridLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0, 0, 400, 600);

        JPanel game;

        for(Boardgame boardgame : Main.collection){
            game = new JPanel(new FlowLayout(FlowLayout.LEFT));
            game.setBounds(0, 0, 400, 200);
            game.setBorder(BorderFactory.createLineBorder(Color.black));
            JButton delete = new JButton("X");
            delete.setName(""+boardgame.getId());
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String id = delete.getName();
                        Database.result("DELETE FROM collection WHERE id = '" +id+ "';");
                        Main.collection= LoadData.loadCollection();
                        CollectionWindow collectionWindow = new CollectionWindow(w.getLocation().x, w.getLocation().y);
                        dispose();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
            JLabel name = new JLabel(boardgame.getName());
            name.setBounds(10, 0, 100, 100);
            JLabel minP = new JLabel("Giocatori: " +boardgame.getMinPlayers()+ " - " + boardgame.getMaxPlayers());
            minP.setBounds(10, 10, 100, 100);
            JLabel avgTime = new JLabel("Durata: " + boardgame.getAvgGameDuration());
            avgTime.setBounds(100, 10, 100, 100);

            URL imageURL;
            try {
                imageURL = new URL(boardgame.getUrl());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ImageIcon img = new ImageIcon(imageURL);
            JLabel label = new JLabel(img);
            label.setBounds(0, 0, 100, 100);
            game.add(label);
            game.add(name);
            game.add(minP);
            game.add(avgTime);
            game.add(delete);
            panel.add(game);
        }

        JButton addBG = new JButton();

        JFrame window = this;
        addBG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AddGameWindow addWindow = new AddGameWindow(1,window,w.getLocation().x, w.getLocation().y);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });



        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, 780, 550);
        this.add(scrollPane, BorderLayout.CENTER);

        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        this.add(addBG);

        this.setBounds(x,y,800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
