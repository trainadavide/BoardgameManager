package View;

import javax.swing.*;

public class CollectionWindow extends JFrame {
    public CollectionWindow(){
        JLabel title = new JLabel("Collection");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
