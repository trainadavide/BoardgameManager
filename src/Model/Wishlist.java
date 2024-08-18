package Model;

import java.util.ArrayList;

public class Wishlist {
    private ArrayList<Boardgame> wishlist = new ArrayList<Boardgame>();

    public void loadWishlist(ArrayList<Boardgame> wishlist){
        this.wishlist = wishlist;
    }

    public void removeFromWishlist(int gameId){
        for (int i=0; i<wishlist.size(); i++){
            if (wishlist.get(i).getId()==gameId)
                wishlist.remove(i);
        }
    }

    public Boardgame getBg(int i){
        return wishlist.get(i);
    }

    public int wishlistSize(){
        return wishlist.size();
    }

    public void addToWishlist(Boardgame boardgame){
        wishlist.add(boardgame);
    }

    public void deleteWishlist(){
        wishlist = new ArrayList<Boardgame>();
    }

    public boolean isInWishlist(int gameid){
        boolean inCollection = false;
        for(int i=0; i<wishlist.size();i++){
            if(wishlist.get(i).getId()==gameid)
                inCollection = true;
        }
        return inCollection;
    }
}
