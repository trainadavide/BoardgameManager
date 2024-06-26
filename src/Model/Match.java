package Model;

import java.util.Vector;

public class Match {
    private final int id;
    private final Boardgame boardgame;
    private final Vector<Player> players;
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

}
