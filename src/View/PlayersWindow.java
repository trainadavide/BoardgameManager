package View;

import javax.swing.*;

public class PlayersWindow extends JFrame {
    public PlayersWindow(){
        JLabel title = new JLabel("Players");
        ImageIcon icon = new ImageIcon(".\\Assets\\Images\\BoardgameManagerIcon.png");
        this.setIconImage(icon.getImage());
        title.setBounds(330,10,200,20);
        this.add(title);

        JButton addBG = new JButton();
        addBG.setText("+");
        addBG.setBounds(715,600,50,50);
        this.add(addBG);

        this.setSize(800,700);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
