package View;

import Controller.Engine;
import Controller.PageNavigation;
import BusinessLogic.Service.CollectionService;
import Model.Boardgame;
import Model.Collection;


import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionView extends JFrame {
    private JButton addGameButton;
    private JButton backButton;

    public CollectionView() {
        setupWindow();
        JPanel mainPanel = createMainPanel();
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createCollectionPanel() {
        JPanel collectionPanel = new JPanel();
        collectionPanel.setLayout(new BoxLayout(collectionPanel, BoxLayout.Y_AXIS));

        Collection collection = Engine.getInstance().getUser().getCollection();

        for(int i=0; i<collection.collectionSize();i++){
            collectionPanel.add(createBoardgameCard(collection.getBg(i)));
        }

        return collectionPanel;
    }

    private JPanel createBoardgameCard(Boardgame bg){
        JPanel bgCard = new JPanel();
        JLabel bgName = new JLabel(bg.getName());
        bgCard.add(bgName);

        return bgCard;
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
        contentPanel.add(createCollectionPanel());

        return contentPanel;
    }

    private JPanel createTitlePanel() {

        JLabel label = new JLabel("COLLECTION", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(label);

        return labelPanel;
    }



    private JPanel createAddGamePanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addGameButton = new JButton("Logout");
        addGameButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToBoardgame();
        });
        buttonPanel.add(addGameButton);
        return buttonPanel;
    }

    private JPanel createBackButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            PageNavigation pageNavigationController = PageNavigation.getIstance(this);
            pageNavigationController.navigateToLogin();
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