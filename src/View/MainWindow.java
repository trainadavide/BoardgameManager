package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame{
    public MainWindow() {
        JLabel title = new JLabel("Boardgame Manager");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(140,10,200,20);
        this.add(title);

        JButton collection = new JButton();
        collection.setText("Collection");
        collection.setBounds(50,50,100,50);
        this.add(collection);

        JButton wishlist = new JButton();
        wishlist.setText("Wishlist");
        wishlist.setBounds(230,50,100,50);
        this.add(wishlist);

        collection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CollectionWindow collectionWindow = new CollectionWindow();
            }

        });
        wishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                WishlistWindow WishlistWindow = new WishlistWindow();

            }

        });

        this.setSize(400,170);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}