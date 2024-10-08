package Model;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Boardgame> collection;

    public void loadCollection(ArrayList<Boardgame> collection){
        this.collection = collection;
    }

    public void removeFromCollection(int gameId){
        for (int i=0; i<collection.size(); i++){
            if (collection.get(i).getId()==gameId)
                collection.remove(i);
        }
    }

    public Boardgame getBg(int i){
        return collection.get(i);
    }

    public int collectionSize(){
        return collection.size();
    }

    public void addToCollection(Boardgame boardgame){
        collection.add(boardgame);
    }

    public void deleteCollection(){
        collection = new ArrayList<Boardgame>();
    }

    public boolean isInCollection(int gameid){
        boolean inCollection = false;
        for(int i=0; i<collection.size();i++){
            if(collection.get(i).getId()==gameid)
                inCollection = true;
        }
        return inCollection;
    }
}
