package View;

import Control.AddPlayerControl;
import Database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import com.toedter.calendar.JDateChooser;


public class CreateMatchWindow extends JFrame{
    public CreateMatchWindow(MatchWindow matchWindow){
        JLabel title = new JLabel("Create Match");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(130,10,200,20);
        this.add(title);

        JLabel game = new JLabel("Game:");
        game.setBounds(50,50,100,20);
        this.add(game);

        //TODO selezione giochi collezione

        JLabel players = new JLabel("Players: ");
        players.setBounds(50,150,100,20);
        this.add(players);

        JLabel date = new JLabel("Date:");
        date.setBounds(50,250,100,20);
        this.add(date);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(150,250,200,20);
        this.add(dateChooser);

        JLabel duration = new JLabel("Duration:");
        duration.setBounds(50,350,100,20);
        this.add(duration);

        JTextField durationField = new JTextField();
        durationField.setBounds(150,350,200,20);
        this.add(durationField);

        JButton create = new JButton();
        create.setText("Create");
        create.setBounds(50,700,100,50);



        this.add(create);

        this.setSize(750,800);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
