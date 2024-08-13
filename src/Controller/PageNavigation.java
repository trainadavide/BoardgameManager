package Controller;

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
        currentFrame = home;
    }

    public void navigateToLogin(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        Login login = new Login(engine);
        login.setSize(frameSize);
        login.setLocation(frameLocation);
        currentFrame = login;
    }

    public void navigateToRegister(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        Register register = new Register(engine);
        register.setSize(frameSize);
        register.setLocation(frameLocation);
        currentFrame = register;
    }

    public void navigateToCollection(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        CollectionView collectionView = new CollectionView(engine);
        collectionView.setSize(frameSize);
        collectionView.setLocation(frameLocation);
        currentFrame = collectionView;
    }

    public void navigateToWishlist(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        WishlistView wishlistView = new WishlistView(engine);
        wishlistView.setSize(frameSize);
        wishlistView.setLocation(frameLocation);
        currentFrame = wishlistView;
    }

    public void navigateToPlayers(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        PlayersView playersView = new PlayersView(engine);
        playersView.setSize(frameSize);
        playersView.setLocation(frameLocation);
        currentFrame = playersView;
    }

    public void navigateToMatch(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        MatchView matchView = new MatchView(engine);
        matchView.setSize(frameSize);
        matchView.setLocation(frameLocation);
        currentFrame = matchView;
    }

    public void navigateToBoardgame() {
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        BoardgameView boardgameView = new BoardgameView(engine);
        boardgameView.setSize(frameSize);
        boardgameView.setLocation(frameLocation);
        currentFrame = boardgameView;
    }

    public void navigateToAddPlayerView(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        AddPlayerView addPlayerView = new AddPlayerView(engine);
        addPlayerView.setSize(frameSize);
        addPlayerView.setLocation(frameLocation);
        currentFrame = addPlayerView;
    }

    public void navigateToAddMatchView(){
        frameSize = currentFrame.getSize();
        frameLocation = currentFrame.getLocation();

        currentFrame.dispose();

        AddMatchView addMatchView = new AddMatchView(engine);
        addMatchView.setSize(frameSize);
        addMatchView.setLocation(frameLocation);
        currentFrame = addMatchView;
    }
}
