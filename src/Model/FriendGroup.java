package Model;

import java.util.ArrayList;

public class FriendGroup {
    private ArrayList<Player> friends = new ArrayList<Player>();

    public void loadFriends(ArrayList<Player> friends){
        this.friends=friends;
    }

    public void removeFromFriends(int playerId){
        for (int i=0; i<friends.size(); i++){
            if (friends.get(i).getId()==playerId)
                friends.remove(i);
        }
    }

    public Player getPlayer(int i){
        return friends.get(i);
    }

    public int friendListSize(){
        return friends.size();
    }

    public void addPlayer(Player player){
        friends.add(player);
    }

    public void deleteFriendsList(){
        friends = new ArrayList<Player>();
    }

    public void editNickname(int playerId, String newNickname){
        for (int i=0; i<friends.size(); i++){
            if (friends.get(i).getId()==playerId)
                friends.get(i).setNickname(newNickname);
        }
    }
}
