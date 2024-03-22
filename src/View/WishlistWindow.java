package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WishlistWindow extends JFrame {
    public WishlistWindow(){
        JLabel title = new JLabel("WishList");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JButton addBG = new JButton();
        addBG.setText("+");
        addBG.setBounds(715,600,50,50);

        addBG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddGameWindow addWindow = new AddGameWindow(false);
            }

        });

        this.add(addBG);

        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
