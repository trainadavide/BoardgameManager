package Model;

public class Player {
    private int id;
    private String nickname;
    public Player(int id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
    public int getId() {
        return id;
    }
    public String getNickname() {
        return nickname;
    }
}
