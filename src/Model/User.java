package Model;

public class User {
    private int id;
    private String email;
    private String password;
    private String username;
    private Collection collection;
    private Wishlist wishlist;
    private MatchLog matchLog;
    private FriendGroup friends;

    public User(int id, String email, String password, String username){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        collection = new Collection();
        wishlist = new Wishlist();
        matchLog = new MatchLog();
        friends = new FriendGroup();
    }

    public User(){
        collection = new Collection();
        wishlist = new Wishlist();
        matchLog = new MatchLog();
        friends = new FriendGroup();
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

    public FriendGroup getFriends() {
        return friends;
    }

    public int getId() {
        return id;
    }

    public String getUsername() { return username; }
}
