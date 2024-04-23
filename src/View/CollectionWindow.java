package View;

import Control.AddCollectionControl;
import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionWindow extends JFrame {
    public CollectionWindow() throws SQLException {
        JLabel title = new JLabel("Collection");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(0, 0, 400, 600);

        ResultSet resultSet = Database.result("SELECT c.id, b.* FROM collection c JOIN boardgame b ON c.id = b.id");
        int i = 1;
        JPanel game;

        while (resultSet.next()) {
            game = new JPanel();
            game.setBounds(0, 0, 400, 200);
            game.setBorder(BorderFactory.createLineBorder(Color.black));
            resultSet.absolute(i);
            JLabel name = new JLabel(resultSet.getString("name"));
            name.setBounds(10, 0, 100, 100);
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
            game.add(name);
            game.add(minP);
            game.add(avgTime);
            panel.add(game);
            i++;
        }

        JButton addBG = new JButton();

        JFrame window = this;
        addBG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AddGameWindow addWindow = new AddGameWindow(true,window);
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

        this.setSize(800, 700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
