package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Match;
import Model.MatchLog;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class MatchView extends JFrame {
    private JButton backButton;
    private JButton addMatchButton;

    public MatchView() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createMatchesPanel() {
        JPanel matchesPanel = new JPanel();
        matchesPanel.setLayout(new BoxLayout(matchesPanel, BoxLayout.Y_AXIS));

        MatchLog matches = Engine.getInstance().getUser().getMatchLog();
        for(int i=0; i<matches.matchListSize(); i++){
            matchesPanel.add(createMatchCard(matches.getMatch(i)));
        }

        return matchesPanel;
    }

    private JPanel createMatchCard(Match match) {
        JPanel mCard = new JPanel(new BorderLayout());
        mCard.setPreferredSize(new Dimension(160, 80));
        mCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        try {
            URL url = new URL(match.getGame().getUrl());
            ImageIcon gameIcon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
            JLabel gameLabel = new JLabel(gameIcon);
            mCard.add(gameLabel, BorderLayout.WEST);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String matchInfo = "<html><div style='text-align: center;'>" +
                "<span style='font-size:12px;'>" +
                "Partita del: " + match.getDate() + "<br>" +
                "Gioco: " + match.getGame().getName() + "<br>";

        JLabel matchLabel = new JLabel(matchInfo, SwingConstants.CENTER);
        mCard.add(matchLabel, BorderLayout.CENTER);


        return mCard;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel contentPanel = createContentPanel();
        JPanel addMatchButtonPanel = createAddMatchButtonPanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addMatchButtonPanel, BorderLayout.EAST);

        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());

        JPanel matchesPanel = createMatchesPanel();
        JScrollPane scrollPane = new JScrollPane(matchesPanel);
        contentPanel.add(scrollPane);

        return contentPanel;
    }

    private JPanel createTitlePanel() {
        JLabel label = new JLabel("MATCHES", SwingConstants.CENTER);
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

    private JPanel createAddMatchButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addMatchButton = new JButton("Add Match");
        addMatchButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToAddMatchView();
        });
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
    }
}