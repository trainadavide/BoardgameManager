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

        Friends friends = Engine.getInstance().getUser().getFriends();
        for(int i=0; i<friends.friendListSize(); i++){
            playersPanel.add(createPlayerCard(friends.getPlayer(i)));
        }

        return playersPanel;
    }

   private JPanel createPlayerCard(Player player) {
    JPanel pCard = new JPanel(new BorderLayout());
    pCard.setPreferredSize(new Dimension(200, 80)); // Riduzione delle dimensioni
    pCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Aggiunta del bordo

    String playerName = "<html><div style='text-align: center;'>" +
            "<span style='font-size:20px;'>" + // Riduzione della dimensione del font
            player.getNickname().toUpperCase() + "</div></html>";

    JLabel playerLabel = new JLabel(playerName, SwingConstants.CENTER);

    pCard.add(playerLabel, BorderLayout.CENTER);

    pCard.add(removeButton(player.getId()), BorderLayout.EAST);

    return pCard;
}

    private JPanel removeButton(int playerId){
        JPanel removePanel = new JPanel(new GridLayout(3,3));
        for (int i = 0; i<4; i++)
            removePanel.add(new JPanel());
        JButton deleteButton = createButton("Remove", null, () -> {
            Engine.getInstance().deletePlayer(playerId);
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToPlayers();
        });
        deleteButton.setPreferredSize(new Dimension(100, 80));
        deleteButton.setBorder(BorderFactory.createMatteBorder(1,1,4,4,Color.BLACK));
        deleteButton.setBackground(new Color(239, 47, 47));
        removePanel.add(deleteButton);
        for (int i = 0; i<4; i++)
            removePanel.add(new JPanel());
        removePanel.setBackground(new Color(163, 226, 232));
        return removePanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel contentPanel = createContentPanel();
        JPanel addPlayerButtonPanel = createAddPlayerButtonPanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addPlayerButtonPanel, BorderLayout.EAST);

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
    }
}