package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Match;
import Model.MatchLog;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class MatchDetailsView extends JFrame {
    private JButton backButton;
    private int matchId;

    public MatchDetailsView(int matchId) {
        this.matchId = matchId;
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();

        Match match = Engine.getInstance().getUser().getMatchLog().getMatchById(matchId);
        JPanel detailPanel = createDetailPanel(match);
        JPanel gameImagePanel = createGameImagePanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(detailPanel, BorderLayout.CENTER);
        mainPanel.add(gameImagePanel, BorderLayout.EAST);

        return mainPanel;
    }



    private JPanel createDetailPanel(Match match) {
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));

        detailPanel.add(createGameTitlePanel(match.getGame().getName()));
        detailPanel.add(createDatePanel(match.getDate().toString()));
        detailPanel.add(createDurationPanel(String.valueOf(match.getDuration())));
        //detailPanel.add(createScoresPanel(match.getPlayers(),match.getPoints());
        detailPanel.add(createWinnerPanel(Engine.getInstance().getMatchWinners(match.getId())));

    return detailPanel;
}


private JPanel createGameImagePanel() {
    JPanel gameImagePanel = new JPanel();
    double k = 2;
    try {
        URL url = new URL(Engine.getInstance().getUser().getMatchLog().getMatchById(matchId).getGame().getUrl());
        ImageIcon originalIcon = new ImageIcon(url);
        int scaledWidth = (int) (originalIcon.getIconWidth() * k);
        int scaledHeight = (int) (originalIcon.getIconHeight() * k);
        ImageIcon gameIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT));
        JLabel gameImageLabel = new JLabel(gameIcon);
        gameImagePanel.add(gameImageLabel);
    } catch (MalformedURLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
    return gameImagePanel;
}

    private JPanel createGameTitlePanel(String gameTitle) {
        JPanel gameTitlePanel = new JPanel();
        JLabel gameTitleLabel = new JLabel(gameTitle.toUpperCase());
        gameTitleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        gameTitlePanel.add(gameTitleLabel);
        return gameTitlePanel;
    }

private JPanel createDatePanel(String date) {
    JPanel datePanel = new JPanel();
    JLabel dateLabel = new JLabel("Date: " + date);
    dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    datePanel.add(dateLabel);
    return datePanel;
}

private JPanel createDurationPanel(String duration) {
    JPanel durationPanel = new JPanel();
    JLabel durationLabel = new JLabel("Duration: " + duration);
    durationLabel.setFont(new Font("Arial", Font.PLAIN, 24));

    durationPanel.add(durationLabel);
    return durationPanel;
}

private JPanel createScoresPanel(ArrayList<Player> players, ArrayList<Integer> points) {
    JPanel scoresPanel = new JPanel();
    scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
    //TODO
    for(int i = 0; i<players.size(); i++){
        JLabel scoreLabel = new JLabel(players.get(i).getNickname() + ": " + points.get(i));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoresPanel.add(scoreLabel);
    }
    return scoresPanel;
}

private JPanel createWinnerPanel(ArrayList<Player> winners) {
    JPanel winnerPanel = new JPanel();
    String winnersString = "";
    if(winners.size()==1){
        winnersString += "WINNER: ";
    } else {
        winnersString += "WINNERS: ";
    }
    for(int i = 0; i<winners.size(); i++){
        winnersString += winners.get(i).getNickname();
        if(i<winners.size()-1){
            winnersString += ", ";
        }
    }
    JLabel winnerLabel = new JLabel(winnersString.toString());
    winnerLabel.setFont(new Font("Arial", Font.PLAIN, 30));
    winnerPanel.add(winnerLabel);
    return winnerPanel;
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