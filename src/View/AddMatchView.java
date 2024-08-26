package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Boardgame;
import Model.Match;
import Model.MatchLog;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class AddMatchView extends JFrame {
    private JButton backButton;

    private JButton addMatchButton;

    private Boardgame selectedGame;

    public AddMatchView() {
        this.selectedGame = Engine.getInstance().getSelectedGame();
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel matchesPanel = createMatchesPanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(matchesPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createMatchesPanel() {
        JPanel matchesPanel = new JPanel();
        matchesPanel.setLayout(new BoxLayout(matchesPanel, BoxLayout.Y_AXIS));

        if(selectedGame!=null){
            try {
                URL url = new URL(selectedGame.getUrl());
                ImageIcon gameIcon = new ImageIcon(url);
                JLabel gameImageLabel = new JLabel(gameIcon);
                matchesPanel.add(gameImageLabel);
            } catch (MalformedURLException e) {
                System.err.println("Errore durante il caricamento dell'immagine del gioco: " + e.getMessage());
            }
            JLabel gameLabel = new JLabel(selectedGame.getName());
            gameLabel.setFont(new Font("Arial", Font.BOLD, 24));
            matchesPanel.add(gameLabel);
            JButton xButton = new JButton("X");
            xButton.addActionListener(e -> {
                Engine.getInstance().setSelectedGame(null);
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToAddMatchView();
            });
            matchesPanel.add(xButton);
        }
        else {
            JButton selectGameButton = new JButton("Select Game");
            selectGameButton.addActionListener(e -> {
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToCollection(true);
            });
            matchesPanel.add(selectGameButton);
        }

        return matchesPanel;
    }

    private JPanel createBackButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToMatch();
        });
        buttonPanel.add(backButton);
        return buttonPanel;
    }




    private JButton createButton(String title, ButtonGroup buttonGroup, Runnable action) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(250, 80));
        if (buttonGroup != null) {
            buttonGroup.add(button);
        }
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