package View;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Control.AddPlayerControl;
import Database.Database;
import Main.Main;
import Control.LoadData;

public class CreatePlayerWindow extends JFrame{
    public CreatePlayerWindow(PlayersWindow playersWindow){
        JLabel title = new JLabel("Create Player");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(130,10,200,20);
        this.add(title);

        JLabel nickname = new JLabel("Nickname:");
        nickname.setBounds(50,50,100,20);
        this.add(nickname);

        JTextField nicknameField = new JTextField();
        nicknameField.setBounds(150,50,200,20);
        this.add(nicknameField);

        JButton create = new JButton();
        create.setText("Create");
        create.setBounds(50,100,100,50);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AddPlayerControl.controlPlayer(nicknameField.getText())){
                    Database.result("INSERT INTO players (nickname) VALUES ('" + nicknameField.getText() + "')");//Inserting the player into the
                    JOptionPane.showMessageDialog(null, "Player created successfully");
                    try {
                        Main.players = LoadData.loadPlayers();
                        PlayersWindow playersWindow1 = new PlayersWindow();
                        playersWindow.dispose();
                    } catch (SQLException ex) {

                    }
                    dispose();
            }
        }
        });

        this.add(create);

        this.setSize(400,200);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
