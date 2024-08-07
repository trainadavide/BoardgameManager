package Controller;

import Model.Collection;
import Model.Friends;
import View.*;

import javax.swing.*;
import java.awt.*;

public class PageNavigation {
    static PageNavigation istance;
    private JFrame currentFrame;
    private Dimension frameSize;
    private Point frameLocation;
    private Engine engine;

    public void setEngine(Engine engine){
        this.engine = engine;
    }

    public Engine getEngine(){
        return engine;
    }

    private PageNavigation(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        this.frameSize = currentFrame.getSize(); // get the size of the current frame
        this.frameLocation = currentFrame.getLocation();
        this.engine=Engine.getInstance();
    }

    public static PageNavigation getIstance(JFrame currentFrame){
        if(istance==null){
            istance =new PageNavigation(currentFrame);
        }
        return istance;
    }

    public void navigateToHome(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        Home home = new Home(engine);
        home.setSize(frameSize);
        home.setLocation(frameLocation);
    }

    public void navigateToCollection(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        CollectionView collectionView = new CollectionView(engine);
        collectionView.setSize(frameSize);
        collectionView.setLocation(frameLocation);
    }

    public void navigateToWishlist(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        WishlistView wishlistView = new WishlistView(engine);
        wishlistView.setSize(frameSize);
        wishlistView.setLocation(frameLocation);
    }

    public void navigateToPlayers(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        PlayersView playersView = new PlayersView(engine);
        playersView.setSize(frameSize);
        playersView.setLocation(frameLocation);
    }

    public void navigateToMatch(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        MatchView matchView = new MatchView(engine);
        matchView.setSize(frameSize);
        matchView.setLocation(frameLocation);
    }

    public void navigateToBoardgame() {
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        BoardgameView boardgameView = new BoardgameView(engine);
        boardgameView.setSize(frameSize);
        boardgameView.setLocation(frameLocation);
    }

    public void navigateToAddPlayerView(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        AddPlayerView addPlayerView = new AddPlayerView(engine);
        addPlayerView.setSize(frameSize);
        addPlayerView.setLocation(frameLocation);
    }

    public void navigateToAddMatchView(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        AddMatchView addMatchView = new AddMatchView(engine);
        addMatchView.setSize(frameSize);
        addMatchView.setLocation(frameLocation);
    }
}
