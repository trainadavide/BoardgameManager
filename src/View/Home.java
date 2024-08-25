package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private JLabel welcomeLabel;
    private JButton logoutButton;
    private JButton collectionButton;
    private JButton wishlistButton;
    private JButton playersButton;
    private JButton matchButton;


    public Home( ){
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel logoutButtonPanel = createLogoutButtonPanel();
        JPanel welcomePanel = createWelcomePanel();
        JPanel contentPanel = createContentPanel();

        mainPanel.add(logoutButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(welcomePanel, BorderLayout.EAST);

        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());
        contentPanel.add(createMenuPanel());

        return contentPanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("HOME", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }

private JPanel createMenuPanel() {
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 15, 15, 15); // Distanza tra i pulsanti

    ButtonGroup buttonGroup = new ButtonGroup();
    PageNavigation pageNavigationController = PageNavigation.getIstance(this);

    collectionButton = createButton("Collection", buttonGroup, () -> {
        pageNavigationController.navigateToCollection(false);
    });

    wishlistButton = createButton("Wishlist", buttonGroup, () -> {
        pageNavigationController.navigateToWishlist();
    });

    playersButton = createButton("Players", buttonGroup, () -> {
        pageNavigationController.navigateToPlayers();
    });

    matchButton = createButton("Match", buttonGroup, () -> {
        pageNavigationController.navigateToMatch();
    });

    gbc.gridx = 0;
    gbc.gridy = 0;
    buttonPanel.add(collectionButton, gbc);

    gbc.gridx = 1;
    buttonPanel.add(wishlistButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    buttonPanel.add(playersButton, gbc);

    gbc.gridx = 1;
    buttonPanel.add(matchButton, gbc);

    return buttonPanel;
}

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new FlowLayout());
        welcomeLabel = new JLabel("Welcome, " + Engine.getInstance().getUser().getUsername());
        welcomePanel.add(welcomeLabel);
        return welcomePanel;
    }

    private JPanel createLogoutButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToLogin();
        });
        buttonPanel.add(logoutButton);
        return buttonPanel;
    }

    private JButton createButton(String title, ButtonGroup buttonGroup, Runnable action) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(250, 80));
        buttonGroup.add(button);
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        return button;
    }

    private void setupWindow() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Assets/BoardgameManager.png");
        setIconImage(icon.getImage());
    }
}
