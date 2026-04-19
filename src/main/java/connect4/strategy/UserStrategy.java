package connect4.strategy;

import connect4.Connect4Panel;
import connect4.players.Player;

public class UserStrategy implements IStrategy{
    public int determineMove(Player myself){
        return myself.getGame().getPanel().getMoveFromPlayer(myself.getPlayer());
    }

    @Override
    public boolean isUser() { return true; }
}