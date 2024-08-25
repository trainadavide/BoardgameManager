package Model;

import java.util.*;

public class Match {
    private final int id;
    private final Boardgame boardgame;
    private Player[] players;
    private int[] points;
    private Date date;
    private int duration;
    private ArrayList<Player> winners;

    public Match(int id, Boardgame boardgame) {
        this.id = id;
        this.boardgame = boardgame;
        players = new Player[boardgame.getMaxPlayers()];
        points = new int[boardgame.getMaxPlayers()];
    }

    public void addPlayer(Player p, int score){
        boolean alreadyIn = false;

        for (int i=0;i< boardgame.getMaxPlayers();i++){
            if(players[i]!=null) {
                if (players[i].getId() == p.getId())
                    alreadyIn = true;
            }
        }

        if(!alreadyIn) {
            int i = 0;
            boolean insert = false;
            do {
                if (players[i] == null) {
                    players[i] = p;
                    points[i] = score;
                    insert = true;
                }
                i++;
            } while (!insert && i < boardgame.getMaxPlayers());
        }
    }

    public void removePlayer(int playerId){
        for(int i=0; i< boardgame.getMaxPlayers(); i++){
            if(players[i].getId()==playerId){
                players[i] = null;
                points[i] = 0;
            }
        }
    }

    public void editScore(int playerId, int updatedScore){
        for(int i=0; i< boardgame.getMaxPlayers(); i++){
            if(players[i].getId()==playerId){
                points[i] = updatedScore;
            }
        }
    }

    public int getId() {
        return id;
    }

    public Boardgame getGame() {
        return boardgame;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int[] getPoints() {
        return points;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Player> getWinner() {
        return winners;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
