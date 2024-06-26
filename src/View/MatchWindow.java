package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Database;

public class MatchWindow extends JFrame {
    public MatchWindow(int x, int y) throws SQLException {
        JFrame w = this;
        JLabel title = new JLabel("Match");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JPanel panel = new JPanel();
        panel.setBounds(0, 50, 400, 600);
        panel.setLayout(new GridLayout(10,20));

        ResultSet resultSet = Database.result("SELECT * FROM Match");



        JButton addBG = new JButton();
        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        addBG.addActionListener(e -> {
            try {
                CreateMatchWindow createMatchWindow = new CreateMatchWindow(this,0,w.getLocation().x, w.getLocation().y,null);
            } catch (SQLException ex) {

            }
        });

        this.add(panel);
        this.add(addBG);
        this.setBounds(x,y,800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
