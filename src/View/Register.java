package View;

import Controller.*;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField validatePasswordField;
    private JTextField emailField;

    public Register() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel signInButtonPanel = createSignInButtonPanel();
        JPanel contentPanel = createContentPanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(signInButtonPanel, BorderLayout.EAST);

        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());
        contentPanel.add(createAddUserPanel());

        return contentPanel;
    }

    private JPanel createAddUserPanel() {
        JPanel addTablePanel = new JPanel(new GridLayout(10, 1));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel validatePasswordLabel = new JLabel("Confirm Password:");


        usernameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JTextField();
        validatePasswordField = new JTextField();


        addTablePanel.add(usernameLabel);
        addTablePanel.add(usernameField);
        addTablePanel.add(emailLabel);
        addTablePanel.add(emailField);
        addTablePanel.add(passwordLabel);
        addTablePanel.add(passwordField);
        addTablePanel.add(validatePasswordLabel);
        addTablePanel.add(validatePasswordField);


        JPanel emptyPanel = new JPanel();
        addTablePanel.add(emptyPanel);

        return addTablePanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("Sign In", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }


    private JPanel createSignInButtonPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController = PageNavigation.getIstance(this);

        JToggleButton addUserButton = createButton("Sign In", buttonGroup, () -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String validatePassword = validatePasswordField.getText();


            if (!password.equals(validatePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Engine.getInstance().register(email, password, username)){
                JOptionPane.showMessageDialog(this, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User already exists", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pageNavigationController.navigateToLogin();
        });

        addUserButton.setSelected(false);

        buttonPanel.add(addUserButton);

        return buttonPanel;
    }

    private JPanel createBackButtonPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(11, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        PageNavigation pageNavigationController =  PageNavigation.getIstance(this);

        JToggleButton backButton = createButton("Back", buttonGroup, pageNavigationController::navigateToLogin);

        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private JToggleButton createButton(String title, ButtonGroup buttonGroup, Runnable action) {
        JToggleButton button = new JToggleButton(title);
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
    }
}
