package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;

public class AddPlayerView extends JFrame {
    private JTextField nicknameField;
    private JButton backButton;
    private JButton addButton;

    public AddPlayerView() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createButtonPanel(), BorderLayout.NORTH);
        mainPanel.add(createNicknamePanel(), BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createNicknamePanel() {
        JPanel nicknamePanel = new JPanel();
        nicknamePanel.setLayout(new GridBagLayout());
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nicknameField = new JTextField(30);
        nicknameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nicknameField.setPreferredSize(new Dimension(300, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        nicknamePanel.add(nicknameLabel, gbc);

        gbc.gridx = 1;
        nicknamePanel.add(nicknameField, gbc);

        return nicknamePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout());

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToHome();
        });
        buttonPanel.add(backButton, BorderLayout.WEST);

        addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setPreferredSize(new Dimension(150, 50));
        addButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            if (!nickname.isEmpty()) {
                Engine.getInstance().addPlayer(nickname);
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToPlayers();
            }
        });
        buttonPanel.add(addButton, BorderLayout.EAST);

        return buttonPanel;
    }

    private void setupWindow() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Assets/BoardgameManager.png");
        setIconImage(icon.getImage());
    }
}
