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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(createNicknamePanel());
        mainPanel.add(createButtonPanel());
        return mainPanel;
    }

    private JPanel createNicknamePanel() {
        JPanel nicknamePanel = new JPanel(new FlowLayout());
        JLabel nicknameLabel = new JLabel("Nickname:");
        nicknameField = new JTextField(20);
        nicknamePanel.add(nicknameLabel);
        nicknamePanel.add(nicknameField);
        return nicknamePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToHome();
        });
        buttonPanel.add(backButton);

        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            if (!nickname.isEmpty()) {
                Engine.getInstance().addPlayer(nickname);
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToPlayers();
            }
        });
        buttonPanel.add(addButton);
        return buttonPanel;
    }

    private void setupWindow() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}