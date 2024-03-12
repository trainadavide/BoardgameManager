package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow {
    public MainWindow() {
        JFrame f=new JFrame();//creating instance of JFrame
        JLabel title = new JLabel("Boardgame Manager");
        title.setBounds(440,10,200,20);
       /* File fontFile = new File("./Assets/Fonts/brocats/brocats.ttf");
        Font titleFont = null;
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT,fontFile);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.setFont(titleFont);*/


        f.add(title);//adding button in JFrame

        f.setSize(1000,700);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
