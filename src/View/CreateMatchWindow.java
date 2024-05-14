package View;

import Control.AddPlayerControl;
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
import java.util.*;

import Main.Main;
import com.toedter.calendar.JDateChooser;


public class CreateMatchWindow extends JFrame{
    JFrame w = this;
    public int gameID=0;

    CreateMatchWindow createMatchWindow;
    MatchWindow mat;
    public CreateMatchWindow(MatchWindow matchWindow, int gameID, int x, int y) throws SQLException {
        this.gameID = gameID;
        mat = matchWindow;
        createMatchWindow= this;
        System.out.println(gameID);
        JLabel title = new JLabel("Create Match");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(130,10,200,20);
        this.add(title);

        JLabel game = new JLabel("Game:");
        game.setBounds(50,50,100,20);
        this.add(game);

        if(gameID==0) {
            JButton selectGame = new JButton("Select");
            selectGame.setBounds(150, 50, 100, 20);
            this.add(selectGame);
            selectGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        AddGameWindow addGameWindow = new AddGameWindow(2, createMatchWindow ,w.getLocation().x, w.getLocation().y);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        else{
            JPanel gamePanel = new JPanel();
            ResultSet rs = Database.result("SELECT * FROM boardgame WHERE id = "+gameID);
            rs.absolute(1);
            System.out.println(rs.getString("name"));

            gamePanel.setBounds(100, 30, 500, 280);
            gamePanel.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel name = new JLabel(rs.getString("name"));
            name.setBounds(10, 0, 100, 100);
            JLabel minP = new JLabel("Giocatori: " +rs.getInt("minPlayers")+ " - " + rs.getInt("maxPlayers"));
            minP.setBounds(10, 10, 100, 100);

            URL imageURL;
            try {
                imageURL = new URL(rs.getString("image"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            ImageIcon img = new ImageIcon(imageURL);
            JLabel label = new JLabel(img);
            label.setBounds(0, 0, 100, 100);

            JButton delete_game = new JButton("X");
            delete_game.setBounds(200, 0, 50, 50);
            delete_game.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                CreateMatchWindow newCreateMatch = new CreateMatchWindow(mat, 0,w.getLocation().x, w.getLocation().y);
                                dispose();
                            } catch (SQLException ex) {

                            }
                            dispose();
                        }
                    }
            );

            gamePanel.add(label);
            gamePanel.add(name);
            gamePanel.add(minP);
            gamePanel.add(delete_game);
            gamePanel.setVisible(true);

            this.add(gamePanel);
        }

        JLabel players = new JLabel("Players: ");
        players.setBounds(50,325,100,20);
        this.add(players);

        JLabel date = new JLabel("Date:");
        date.setBounds(50,425,100,20);
        this.add(date);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(150,425,200,20);
        this.add(dateChooser);

        JLabel duration = new JLabel("Duration:");
        duration.setBounds(50,475,100,20);
        this.add(duration);

        JTextField durationField = new JTextField();
        durationField.setBounds(150,475,200,20);
        this.add(durationField);

        JButton create = new JButton();
        create.setText("Create");
        create.setBounds(50,675,100,50);
        this.add(create);

        this.setBounds(x,y,800,800);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }

    public void setGameID(int gameID) throws SQLException {
        CreateMatchWindow newCreateMatch = new CreateMatchWindow(mat, gameID,w.getLocation().x, w.getLocation().y);
        dispose();
    }
}
