package Model;

import java.util.Vector;

public class Match {
    private final int id;
    private final Boardgame boardgame;
    private Vector<Player> players;
    private Vector<Integer> points;

    public Match(int id, Boardgame boardgame) {
        this.id = id;
        this.boardgame = boardgame;

        players = new Vector<>(boardgame.getMaxPlayers());
        points = new Vector<>(boardgame.getMaxPlayers());
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void removePlayer(int playerId){
        for (int i=0; i<players.size(); i++){
            if (players.get(i).getId() == playerId){
                players.remove(playerId);
            }
        }
    }


}
