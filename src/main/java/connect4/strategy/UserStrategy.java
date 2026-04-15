package connect4.strategy;

import connect4.players.Player;

public class UserStrategy implements IStrategy{
    public int determineMove(Player myself){
        //get user move
        return 0;
    }

    @Override
    public boolean isUser() { return true; }
}