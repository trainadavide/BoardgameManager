package Model;

public class Boardgame {
    private int id;
    private String name;
    private int minPlayers;
    private int maxPlayers;
    private int avgGameDuration;
    private boolean competitive;

    private String url;

    public Boardgame(int id, String name, int minPlayers, int maxPlayers, int avgGameDuration, boolean competitive, String url) {
        this.id = id;
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.competitive = competitive;
        this.avgGameDuration = avgGameDuration;
        this.url=url;

    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvgGameDuration() {
        return avgGameDuration;
    }

    public boolean isCompetitive() {
        return competitive;
    }

    public String getUrl() {
        return url;
    }
}

