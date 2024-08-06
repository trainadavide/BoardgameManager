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
        collection = new Collection();
        wishlist = new Wishlist();
        matchLog = new MatchLog();
        friends = new Friends();
    }

    public User(){
        collection = new Collection();
        wishlist = new Wishlist();
        matchLog = new MatchLog();
        friends = new Friends();
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

    public int getId() {
        return id;
    }
}
