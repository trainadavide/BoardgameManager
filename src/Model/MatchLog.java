package Model;

import java.util.ArrayList;

public class MatchLog {
    private ArrayList<Match> matches;

    public void loadMatches(ArrayList<Match> matches){
        this.matches = matches;
    }

    public void removeMatch(int matchId){
        for (int i=0; i<matches.size(); i++){
            if (matches.get(i).getId()==matchId)
                matches.remove(i);
        }
    }

    public void addMatch(Match match){
        matches.add(match);
    }

    public void deleteAllMatch(){
        matches = new ArrayList<Match>();
    }
}
