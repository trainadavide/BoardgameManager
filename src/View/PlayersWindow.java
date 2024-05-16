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
    public PlayersWindow(int x, int y, boolean scope, JFrame previousWindow, int pos) throws SQLException {
        JFrame w = this;
        JLabel title = new JLabel("Players");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        if(!scope){
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
                            PlayersWindow playersWindow = new PlayersWindow(w.getLocation().x, w.getLocation().y,false,null,0);
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
        else{
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
                JButton select = new JButton("+");
                select.setName(""+p.getId());
                select.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ResultSet rs = Database.result("SELECT nickname FROM players WHERE id = "+select.getName());
                        try {
                            rs.absolute(1);
                            ((CreateMatchWindow) previousWindow).players[pos] = new Player(Integer.parseInt(select.getName()), rs.getString("nickname"));
                            CreateMatchWindow cw = new CreateMatchWindow(((CreateMatchWindow) previousWindow).mat, ((CreateMatchWindow) previousWindow).gameID, w.getLocation().x, w.getLocation().y, ((CreateMatchWindow) previousWindow).players);
                            dispose();
                        }
                        catch (SQLException ex) {

                        }
                    }
                });

                JLabel nickname = new JLabel(p.getNickname());
                nickname.setBounds(10, 0, 20, 20);
                internalPanel.add(nickname);
                internalPanel.add(select);
                internalPanel.setBounds(0,0,100,50);
                player.add(internalPanel);
                player.setBounds(0, 0, 160, 65);
                panel.add(player);
                System.out.println(p.getNickname());
            }

            this.add(panel);
            this.setBounds(x,y,800,700);
            this.setLayout(null);//using no layout managers
            this.setVisible(true);//making the frame visible
            this.setResizable(false);
        }


    }

}
