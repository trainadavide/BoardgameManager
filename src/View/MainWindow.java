package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {
    public MainWindow() {
        JFrame f = new JFrame();//creating instance of JFrame
        JLabel title = new JLabel("Boardgame Manager");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        f.setIconImage(icon.getImage());
        title.setBounds(440,10,200,20);

        JButton collection = new JButton("Collection");
        collection.setBounds(350,50,100,50);
        collection.setBackground(new Color(32,177,185,80));

        JButton wishlist = new JButton("Wishlist");
        wishlist.setBounds(530,50,100,50);
        wishlist.setBackground(new Color(32,177,185,80));


        f.add(title);
        f.add(collection);
        f.add(wishlist);

        f.setSize(1000,700);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
