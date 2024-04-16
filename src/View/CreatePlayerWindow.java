package View;
import javax.swing.*;

public class CreatePlayerWindow extends JFrame{
    public CreatePlayerWindow(){
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
        this.add(create);

        JButton cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(200,100,100,50);
        this.add(cancel);

        this.setSize(400,200);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
        this.setResizable(false);
    }
}
