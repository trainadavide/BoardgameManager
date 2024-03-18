package Model;

public class Boardgame {
    private int id;
    private String name;
    private int minPlayers;
    private int maxPlayers;
    private int avgGameDuration;
    private boolean competitive;

    public Boardgame(int id, String name, int minPlayers, int maxPlayers, int avgGameDuration, boolean competitive) {
        this.id = id;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.competitive = competitive;
        this.avgGameDuration = avgGameDuration;

    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }
}
