package Connect4.Players;
import Connect4.Board;
import Connect4.Color;

public class AIBot extends Player{
    public AIBot(Color color, Board board, int player){ super(color, board, player); }

    @Override
    public int determineMove(){
        int defenseRating = getDefenseRating();
        int attackRating = getAttackRating();

        //AI should only defend if P1 is about to win, attack otherwise
        if(defenseRating >= 3 && attackRating < 3) return getDefensePosition();
        else return getAttackPosition();
    }

    private int getDefensePosition(){
        //get the best defense position (the column where a coin placed would stop P1's biggest streak)
        return 0;
    }

    private int getAttackPosition(){
        //get the best attack position (the column where a coin placed would increase P2's biggest streak)
        return 0;
    }

    private int getDefenseRating(){
        //analyze board to determine how effective a defense would be (how many coins P1 has in a row)
        return 0;
    }

    private int getAttackRating(){
        //analyze board to determine how effective an attack would be(how many coins P2 has in a row)
        return 0;
    }
}
