package View;

import Controller.Engine;
import Controller.PageNavigation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Login extends JFrame {

    private JTextField emailField;
    private JTextField passwordField;

    public Login() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);

    }
    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel signInButtonPanel = createSignInButtonPanel();
        JPanel loginButtonPanel = createLoginButtonPanel();
        JPanel contentPanel = createContentPanel();

        mainPanel.add(signInButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(loginButtonPanel, BorderLayout.EAST);
        return mainPanel;
    }

    private JPanel createSignInButtonPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController = PageNavigation.getIstance(this);

        JToggleButton signInButton = createButton("Sign In", buttonGroup, pageNavigationController::navigateToRegister);

        buttonPanel.add(signInButton);

        return buttonPanel;
    }

    private JPanel createLoginButtonPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController = PageNavigation.getIstance(this);


        JToggleButton loginButton = createButton("Login", buttonGroup, () -> {
            // Esegui il login
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Engine.getInstance().login(email, password)) {
                System.out.println("Credenziali non valide");
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("Login effettuato con successo");
                pageNavigationController.navigateToHome();
            }

        });

        loginButton.setSelected(false);

        buttonPanel.add(loginButton);

        return buttonPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());
        contentPanel.add(createLogUserPanel());

        return contentPanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("Access", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }

    private JPanel createLogUserPanel() {
        JPanel addTablePanel = new JPanel(new GridLayout(12, 1));

        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");


        emailField = new JTextField();
        passwordField = new JTextField();

        addTablePanel.add(emailLabel);
        addTablePanel.add(emailField);
        addTablePanel.add(passwordLabel);
        addTablePanel.add(passwordField);

        JPanel emptyPanel = new JPanel();
        addTablePanel.add(emptyPanel);

        return addTablePanel;
    }

    private void setupWindow() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Assets/BoardgameManager.png");
        setIconImage(icon.getImage());
    }

    private JToggleButton createButton(String title, ButtonGroup buttonGroup, Runnable action) {
        JToggleButton button = new JToggleButton(title);
        buttonGroup.add(button);
        if (action != null) {
            button.addActionListener(e -> action.run());
        }
        return button;
    }
}
