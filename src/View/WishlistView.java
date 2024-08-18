package View;

import Controller.Engine;
import Controller.PageNavigation;
import Model.Boardgame;
import Model.Collection;
import Model.Wishlist;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WishlistView extends JFrame {
    //TODO

    private JButton addGameButton;
    private JButton backButton;

    public WishlistView() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createWishlistPanel() {
        JPanel wishlistPanel = new JPanel();
        wishlistPanel.setLayout(new BoxLayout(wishlistPanel, BoxLayout.Y_AXIS));

        Wishlist wishlist = Engine.getInstance().getUser().getWishlist();

        for(int i=0; i<wishlist.wishlistSize();i++){
            wishlistPanel.add(createBoardgameCard(wishlist.getBg(i)));
            wishlistPanel.add(new JPanel());
        }

        return wishlistPanel;
    }

    private JPanel createBoardgameCard(Boardgame bg){
        JPanel bgCard = new JPanel(new BorderLayout());
        bgCard.setPreferredSize(new Dimension(600, 200));
        try {
            URL url = new URL(bg.getUrl());
            ImageIcon imageIcon = new ImageIcon(url);
            JLabel imageLabel = new JLabel(imageIcon);
            bgCard.add(imageLabel, BorderLayout.WEST);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String gameDetails = "<html><div style='text-align: center;'>" + "<span style='font-size:26px;'>" + bg.getName().toUpperCase() + "</span><br>Players: " + bg.getMinPlayers() + " - " + bg.getMaxPlayers() + "<br>Duration: " + bg.getAvgGameDuration() + " min.</div></html>";
        JLabel detailsLabel = new JLabel(gameDetails);
        detailsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        detailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bgCard.add(detailsLabel, BorderLayout.CENTER);

        bgCard.add(removeButton(bg.getId()), BorderLayout.EAST);

        bgCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return bgCard;
    }

    private JPanel removeButton(int gameId){
        JPanel removePanel = new JPanel(new GridLayout(3,3));
        for (int i = 0; i<4; i++)
            removePanel.add(new JPanel());
        JButton deleteButton = createButton("Remove", null, () -> {
            Engine.getInstance().removeFromWishlist(gameId);
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToWishlist();
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
        JPanel addGamePanel = createAddGamePanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(addGamePanel, BorderLayout.EAST);


        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());

        JPanel collectionPanel = createWishlistPanel();
        JScrollPane scrollPane = new JScrollPane(collectionPanel);
        contentPanel.add(scrollPane);

        return contentPanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("WISHLIST", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }



    private JPanel createAddGamePanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addGameButton = new JButton("Add Game");
        addGameButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToBoardgame(Engine.getInstance().getAllBoardgames(),false);
        });
        buttonPanel.add(addGameButton);
        return buttonPanel;
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
