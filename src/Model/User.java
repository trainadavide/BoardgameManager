package Model;

public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private Collection collection;
    private Wishlist wishlist;
    private MatchLog matchLog;
    private Friends friends;

    public User(int id, String email, String password, String username){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Collection getCollection() {
        return collection;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public MatchLog getMatchLog() {
        return matchLog;
    }

    public Friends getFriends() {
        return friends;
    }
}
