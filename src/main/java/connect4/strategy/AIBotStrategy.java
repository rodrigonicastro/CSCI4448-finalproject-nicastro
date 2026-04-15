package Connect4.Strategy;

import Connect4.Board;
import Connect4.Players.Player;

public class AIBotStrategy implements IStrategy{
    public int determineMove(Player myself){
        int defenseRating = myself.getDefenseRating();
        int attackRating = myself.getAttackRating();

        //AI should only defend if opponent is about to win, attack otherwise
        if(defenseRating >= 3 && attackRating < 3) return myself.getDefensePosition();
        else return myself.getAttackPosition();
    }

    @Override
    public boolean isAIBot(){ return true; }
}