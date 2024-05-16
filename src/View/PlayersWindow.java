package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;
import Main.Main;
import Control.LoadData;
import Model.Player;

public class PlayersWindow extends JFrame {
    public PlayersWindow(int x, int y) throws SQLException {
        JFrame w = this;
        JLabel title = new JLabel("Players");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 400, 600);
        panel.setLayout(new GridLayout(10,20));

        JPanel player;
        JPanel internalPanel;

        for(Player p : Main.players){
            player = new JPanel();
            player.setBounds(0, 0, 20, 20);
            player.setBorder(BorderFactory.createEmptyBorder(0,-30,-15,-30));
            internalPanel = new JPanel();
            internalPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            JButton delete = new JButton("X");
            delete.setName(""+p.getId());
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String id = delete.getName();
                        Database.result("DELETE FROM players WHERE id = '" +id+ "';");
                        Main.players = LoadData.loadPlayers();
                        PlayersWindow playersWindow = new PlayersWindow(w.getLocation().x, w.getLocation().y);
                        dispose();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

            JLabel nickname = new JLabel(p.getNickname());
            nickname.setBounds(10, 0, 20, 20);
            internalPanel.add(nickname);
            internalPanel.add(delete);
            internalPanel.setBounds(0,0,100,50);
            player.add(internalPanel);
            player.setBounds(0, 0, 160, 65);
            panel.add(player);
            System.out.println(p.getNickname());
        }


        JButton addBG = new JButton();
        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        addBG.addActionListener(e -> {
            CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow(this,w.getLocation().x, w.getLocation().y);
        });

        this.add(panel);
        this.add(addBG);
        this.setBounds(x,y,800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
