package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class Match {
    private final int id;
    private final Boardgame boardgame;

    private Player[] players;
    private int[] points;

    public Match(int id, Boardgame boardgame) {
        this.id = id;
        this.boardgame = boardgame;
        players = new Player[boardgame.getMaxPlayers()];
        points = new int[boardgame.getMaxPlayers()];
    }

    public void addPlayer(Player p, int score){
        boolean alreadyIn = false;

        for (int i=0;i< boardgame.getMaxPlayers();i++){
            if(players[i].getId()==p.getId())
                alreadyIn = true;
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
}
