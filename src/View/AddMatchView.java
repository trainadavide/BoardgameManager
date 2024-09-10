package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Boardgame;
import Model.Match;
import Model.MatchLog;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class AddMatchView extends JFrame {
    private JButton backButton;

    private JButton addMatchButton;

    private Boardgame selectedGame;

    private ArrayList<JComboBox<String>> playerSelectors;

    private ArrayList<JTextField> scoresFields = new ArrayList<>();

    private JTextField durationField;

    private JTextField dateField;


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

    JScrollPane scrollPane = new JScrollPane(matchesPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    mainPanel.add(backButtonPanel, BorderLayout.WEST);
    mainPanel.add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane instead of the matchesPanel
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
            matchesPanel.add(PlayerSelectors());

            JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new panel with FlowLayout
            JLabel durationLabel = new JLabel("Duration: "); // Create a new JLabel for "Duration: "
            durationPanel.add(durationLabel); // Add the JLabel to the panel
            durationField = new JTextField(5); // Create a new JTextField for the duration input
            durationPanel.add(durationField); // Add the JTextField to the panel
            matchesPanel.add(durationPanel); // Add the panel to the matchesPanel

            JPanel addButtonPanel = createAddMatchButtonPanel();
            matchesPanel.add(addButtonPanel);


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


    private JPanel PlayerSelectors() {
        System.out.println("Max: "+selectedGame.getMaxPlayers());
        playerSelectors = new ArrayList<>();
        JPanel selectorsPanel = new JPanel();
        selectorsPanel.setLayout(new BoxLayout(selectorsPanel, BoxLayout.Y_AXIS)); // Set the layout to BoxLayout with vertical orientation
        if (selectedGame != null) {
            int maxPlayers = selectedGame.getMaxPlayers();
            ArrayList<Player> players = new ArrayList<>();
            for(int i=0; i<Engine.getInstance().getUser().getFriends().friendListSize(); i++){
                Player player = Engine.getInstance().getUser().getFriends().getPlayer(i);
                players.add(player);
            }
            for (int i = 0; i < maxPlayers; i++) {
                ArrayList<String> playerNames = new ArrayList<>();
                playerNames.add("NULL");
                for (Player player : players) {
                    playerNames.add(player.getNickname());
                }
                JComboBox<String> playerSelector = new JComboBox<>(new DefaultComboBoxModel<>(playerNames.toArray(new String[0])));
                playerSelector.addActionListener(e -> {
                    String selectedPlayer = (String) playerSelector.getSelectedItem();
                    removePlayerFromOtherSelectors(selectedPlayer, playerSelector);
                });
                playerSelectors.add(playerSelector);

                JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a new panel with FlowLayout
                playerPanel.add(playerSelector); // Add the JComboBox to the panel

                JLabel scoreLabel = new JLabel("Score: "); // Create a new JLabel for "Score: "
                playerPanel.add(scoreLabel); // Add the JLabel to the panel

                JTextField scoreField = new JTextField(5); // Create a new JTextField for the score input
                playerPanel.add(scoreField); // Add the JTextField to the panel
                scoresFields.add(scoreField);

                selectorsPanel.add(playerPanel); // Add the panel to the selectorsPanel
            }
        }
        add(selectorsPanel, BorderLayout.CENTER); // Add the panel to the JFrame
        return selectorsPanel;
    }
    private void removePlayerFromOtherSelectors(String player, JComboBox<String> currentSelector) {
        for (JComboBox<String> selector : playerSelectors) {
            if (selector != currentSelector) {
                selector.removeItem(player);
            }
        }
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

    private JPanel createAddMatchButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addMatchButton = new JButton("Add");

        addMatchButton.addActionListener(e -> {
            int[] playersId = new int[selectedGame.getMaxPlayers()];
            int[] scores = new int[selectedGame.getMaxPlayers()];
            int pId;
            for (int i=0; i<selectedGame.getMaxPlayers(); i++){
                if(playerSelectors.get(i).getName() != "NULL") {
                    pId = Engine.getInstance().getPlayerIdByNickname(playerSelectors.get(i).getSelectedItem().toString());
                    playersId[i] = pId;
                    scores[i] = Integer.parseInt(scoresFields.get(i).getText());
                }
            }
            int duration = Integer.parseInt(durationField.getText());

            LocalDate localDate = LocalDate.now();
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            Engine.getInstance().addMatch(selectedGame.getId(),playersId,scores,date,duration);
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToMatch();
        });
        addMatchButton.setSize(100,50);
        buttonPanel.add(addMatchButton);
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