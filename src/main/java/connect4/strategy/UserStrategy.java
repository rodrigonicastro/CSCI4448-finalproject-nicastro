package Connect4.Strategy;

import Connect4.Players.Player;
import Connect4.Strategy.IStrategy;

public class UserStrategy implements IStrategy{
    public int determineMove(Player myself){
        //get user move
        return 0;
    }

    @Override
    public boolean isUser() { return true; }
}