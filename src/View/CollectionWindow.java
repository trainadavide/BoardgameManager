package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollectionWindow extends JFrame {
    public CollectionWindow(){
        JLabel title = new JLabel("Collection");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JButton addBG = new JButton();

        addBG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AddGameWindow addWindow = new AddGameWindow(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        this.add(addBG);

        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
