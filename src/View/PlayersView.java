package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Collection;
import Model.Friends;
import Model.Player;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayersView extends JFrame {
    private JButton backButton;
    private JButton addPlayerButton;

    public PlayersView() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createPlayersPanel() {
        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

        //TODO devo prendere la lista di player dell'utente e visualizzarli con un for
        Friends friends = Engine.getInstance().getUser().getFriends();
        for(int i=0; i<friends.friendListSize(); i++){
            playersPanel.add(createPlayerCard(friends.getPlayer(i)));
        }

        return playersPanel;
    }

    private JPanel createPlayerCard(Player player) {
        JPanel pCard = new JPanel(new BorderLayout());
        pCard.setPreferredSize(new Dimension(300, 100));

        String playerName = "<html><div style='text-align: center;'>" +
                "<span style='font-size:26px;'>" +
                player.getNickname().toUpperCase() + "</div></html>";

        JLabel playerLabel = new JLabel(playerName, SwingConstants.CENTER);
        pCard.add(playerLabel, BorderLayout.CENTER);

        return pCard;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel contentPanel = createContentPanel();
        JPanel addPlayerButtonPanel = createAddPlayerButtonPanel();  // Pannello per il pulsante "Add Player"

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addPlayerButtonPanel, BorderLayout.EAST);  // Aggiunta del pulsante "Add Player"

        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());

        JPanel playersPanel = createPlayersPanel();
        JScrollPane scrollPane = new JScrollPane(playersPanel);
        contentPanel.add(scrollPane);

        return contentPanel;
    }

    private JPanel createTitlePanel() {
        JLabel label = new JLabel("PLAYERS", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }

    private JPanel createBackButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToHome();
        });
        buttonPanel.add(backButton);
        return buttonPanel;
    }

    private JPanel createAddPlayerButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addPlayerButton = new JButton("Add Player");
        addPlayerButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToAddPlayerView();
        });
        buttonPanel.add(addPlayerButton);
        return buttonPanel;
    }

    private void setupWindow() {
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}