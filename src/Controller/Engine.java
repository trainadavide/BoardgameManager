package Controller;

import BusinessLogic.Service.*;
import Model.Boardgame;
import Model.Match;
import Model.Player;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Engine {
    private static Engine istance;
    private User user;
    private ServiceFactory sf;


    //CORE FUNCTION -------------------------------------------------------
    private Engine() {
        sf =  ServiceFactory.getInstance();
    }
    public User getUser() {
        return user;
    }
    public static Engine getInstance() {
        if(istance==null)
            istance = new Engine();
        return istance;
    }

    // USER FUNCTION ------------------------------------------------------
    public boolean login(String email, String password){
        boolean logged = false;
        try{
            UserService userService = (UserService) sf.getService(sf.USER_SERVICE);
            if(userService.checkCredentials(email,password)){
                userService.login(email);
                this.user = userService.getCurrentUser();
                logged = true;
            }
            else {
                System.out.println("Credenziali errate");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante il login: "+e.getMessage());
        }
        return logged;
    }
    public boolean register(String email, String password, String username){
        boolean registered = false;
        UserService userService = (UserService) sf.getService(sf.USER_SERVICE);
        if(userService.checkEmailAlreadyUsed(email)){
            userService.register(email,password,username);
            this.user = userService.getCurrentUser();
            registered = true;
        }
        else {
            System.out.println("Credenziali errate");
        }
        return registered;
    }
    public void logout() {
        user=null;
    }

    // COLLECTION ALTER FUNCTION --------------------------------------------
    public boolean addToCollection(int gameId){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        return collectionService.addGameToCollection(gameId);
    }
    public boolean addToWishlist(int gameId){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        return wishlistService.addGameToWishlist(gameId);
    }
    public boolean removeFromCollection(int gameId){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        return collectionService.removeGameFromCollection(gameId);
    }

    // WISHLIST ALTER FUNCTION --------------------------------------------
    public boolean removeFromWishlist(int gameId){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        return wishlistService.removeGameFromWishlist(gameId);
    }
    public boolean deleteCollection(){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        return collectionService.deleteCollection();
    }
    public boolean deleteWishlist(){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        return wishlistService.deleteWishlist();
    }

    // COLLECTION SEARCH FUNCTION --------------------------------------------
    public ArrayList<Boardgame> searchFromCollectionByName(String name){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        ResultSet rs = collectionService.getBoardgamesByName(name);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromCollectionByMinDuration(int minDuration){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        ResultSet rs = collectionService.getBoardgamesByMinDuration(minDuration);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromCollectionByMaxDuration(int maxDuration){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        ResultSet rs = collectionService.getBoardgamesByMaxDuration(maxDuration);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromCollectionByPlayersNumber(int playerNumber){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        ResultSet rs = collectionService.getBoardgamesByPlayer(playerNumber);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromCollectionByCompetitive(boolean competitive){
        CollectionService collectionService = (CollectionService) sf.getService(sf.COLLECTION_SERVICE);
        ResultSet rs = collectionService.getCompetitiveBoardGame(competitive);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }

    // WISHLIST SEARCH FUNCTION --------------------------------------------
    public ArrayList<Boardgame> searchFromWishlistByName(String name){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        ResultSet rs = wishlistService.getBoardgamesByName(name);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromWishlistByMinDuration(int minDuration){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        ResultSet rs = wishlistService.getBoardgamesByMinDuration(minDuration);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromWishlistByMaxDuration(int maxDuration){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        ResultSet rs = wishlistService.getBoardgamesByMaxDuration(maxDuration);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromWishlistByPlayersNumber(int playerNumber){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        ResultSet rs = wishlistService.getBoardgamesByPlayer(playerNumber);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }
    public ArrayList<Boardgame> searchFromWishlistByCompetitive(boolean competitive){
        WishlistService wishlistService = (WishlistService) sf.getService(sf.WISHLIST_SERVICE);
        ResultSet rs = wishlistService.getCompetitiveBoardGame(competitive);
        BoardgameService boardgameService = (BoardgameService) sf.getService(sf.BOARDGAME_SERVICE);
        ArrayList<Boardgame> gameList = new ArrayList<>();
        try{
            Boardgame boardgame;
            while (rs.next()){
                boardgame = boardgameService.createBoardgameFromId(rs.getInt("gameid"));
                gameList.add(boardgame);
            }
        }catch (SQLException e){

        }
        return gameList;
    }


    // MATCHLIST ALTER FUNCTION -------------------------------------------

    public boolean addMatch(int gameId, int[] playersId, int[] points, Date date, int duration){
        MatchService matchService = (MatchService) sf.getService(sf.MATCH_SERVICE);
        return matchService.addMatch(gameId, playersId, points, date, duration);
    }
    public boolean removeMatch(int matchId){
        MatchService matchService = (MatchService) sf.getService(sf.MATCH_SERVICE);
        return matchService.removeMatch(matchId);
    }

    // MATCHLIST SEARCH FUNCTION -------------------------------------------

    public ArrayList<Match> selectMatchesByGame (int gameId){
        MatchService matchService = (MatchService) sf.getService(sf.MATCH_SERVICE);
        return matchService.getMatchesByGame(gameId);
    }

    // MATCH ALTER FUNCTION ------------------------------------------------

    public boolean editPlayerScore(int matchId, int playerId, int updatedScore){
        MatchPlayerService matchPlayerService = (MatchPlayerService) sf.getService(sf.MATCH_PLAYER_SERVICE);
        return matchPlayerService.editScore(matchId,playerId,updatedScore);
    }

    public boolean addPlayerToMatch(int matchId, int playerId, int score){
        MatchPlayerService matchPlayerService = (MatchPlayerService) sf.getService(sf.MATCH_PLAYER_SERVICE);
        return matchPlayerService.addPlayerToMatch(matchId,playerId,score);
    }

    public boolean removePlayerFromMatch(int matchId, int playerId){
        MatchPlayerService matchPlayerService = (MatchPlayerService) sf.getService(sf.MATCH_PLAYER_SERVICE);
        return matchPlayerService.removePlayerFromMatch(matchId, playerId);
    }

    // MATCHLIST SEARCH FUNCTION -------------------------------------------

    public ArrayList<Player> getMatchWinners(int matchId){
        MatchPlayerService matchPlayerService = (MatchPlayerService) sf.getService(sf.MATCH_PLAYER_SERVICE);
        return matchPlayerService.getWinners(matchId);
    }

}
