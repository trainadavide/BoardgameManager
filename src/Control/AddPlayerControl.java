package Control;

public class AddPlayerControl {

    public static boolean controlPlayer(String nickname){
        if(nickname.length() < 3 || nickname.length() > 20){
            return false;
        }
        return true;
    }


}
