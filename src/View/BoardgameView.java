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
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardgameView extends JFrame {
    private JButton backButton;
    private ArrayList<Boardgame> showingBoardgames;

    private boolean collection;
    public BoardgameView(ArrayList<Boardgame> showingBoardgames, boolean collection) {
        setupWindow();
        this.showingBoardgames = showingBoardgames;
        this.collection = collection;
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createBoardgamePanel() {
        JPanel boardgamePanel = new JPanel();
        boardgamePanel.setLayout(new BoxLayout(boardgamePanel, BoxLayout.Y_AXIS));

        if(collection){
            Collection collection = Engine.getInstance().getUser().getCollection();
            for(int i=0; i<showingBoardgames.size();i++){
                if(!collection.isInCollection(showingBoardgames.get(i).getId())){
                    boardgamePanel.add(createBoardgameCard(showingBoardgames.get(i)));
                    boardgamePanel.add(new JPanel());
                }
            }
        }
        else {
            Wishlist wishlist = Engine.getInstance().getUser().getWishlist();
            for(int i=0; i<showingBoardgames.size();i++){
                if(!wishlist.isInWishlist(showingBoardgames.get(i).getId())){
                    boardgamePanel.add(createBoardgameCard(showingBoardgames.get(i)));
                    boardgamePanel.add(new JPanel());
                }
            }
        }

        return boardgamePanel;
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

        bgCard.add(addButton(bg.getId()), BorderLayout.EAST);

        bgCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return bgCard;
    }

    private JPanel addButton(int gameId){
        JPanel addPanel = new JPanel(new GridLayout(3,3));
        for (int i = 0; i<4; i++)
            addPanel.add(new JPanel());
        JButton addButton;
        if(collection){
            addButton = createButton("Add to Collection", null, () -> {
                Engine.getInstance().addToCollection(gameId);
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToCollection();
            });
        }
        else {
            addButton = createButton("Add to Wishlist", null, () -> {
                Engine.getInstance().addToWishlist(gameId);
                PageNavigation pageNavigationController = PageNavigation.getIstance(this);
                pageNavigationController.navigateToWishlist();
            });
        }

        addButton.setPreferredSize(new Dimension(100, 80));
        addButton.setBorder(BorderFactory.createMatteBorder(1,1,4,4,Color.BLACK));
        addButton.setBackground(new Color(169, 239, 47));
        addPanel.add(addButton);
        for (int i = 0; i<4; i++)
            addPanel.add(new JPanel());
        addPanel.setBackground(new Color(163, 226, 232));
        return addPanel;
    }
    private JPanel createMainPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel backButtonPanel = createBackButtonPanel();
        JPanel contentPanel = createContentPanel();

        mainPanel.add(backButtonPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(createTitlePanel());
        contentPanel.add(createSearchPanel());


        JPanel collectionPanel = createBoardgamePanel();
        JScrollPane scrollPane = new JScrollPane(collectionPanel);
        contentPanel.add(scrollPane);

        return contentPanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("BOARDGAMES", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout());

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> {
            String search = searchField.getText();
            ArrayList<Boardgame> searchResult = Engine.getInstance().getBoardgameByName(search);
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToBoardgame(searchResult, collection);
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel createBackButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            if (collection)
                pageNavigationController.navigateToCollection();
            else
                pageNavigationController.navigateToWishlist();
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
