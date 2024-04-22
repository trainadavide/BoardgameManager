package View;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;

public class PlayersWindow extends JFrame {
    public PlayersWindow() throws SQLException {
        JLabel title = new JLabel("Players");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 400, 600);
        panel.setLayout(new GridLayout(10,20));

        ResultSet resultSet = Database.result("SELECT * FROM players");
        int i=1;
        JPanel player;
        while (resultSet.next()) {
            player = new JPanel();
            player.setBounds(0, 0, 20, 20);
            player.setBorder(BorderFactory.createLineBorder(Color.black));
            resultSet.absolute(i);
            JLabel nickname = new JLabel(resultSet.getString("nickname"));
            nickname.setBounds(10, 0, 20, 20);
            player.add(nickname);
            panel.add(player);
            i++;
        }


        JButton addBG = new JButton();
        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        addBG.addActionListener(e -> {
            CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();
        });

        this.add(panel);
        this.add(addBG);
        this.setSize(800, 700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
