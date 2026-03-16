package Connect4.Players;
import Connect4.Board;
import Connect4.Color;

import java.util.Random;

public class RandomBot extends Player{
    private final Random rand = new Random();

    public RandomBot(Color color, Board board, int player){ super(color, board, player); }

    @Override
    public int determineMove(){
        int position = rand.nextInt(board.getNumColumns());

        while(!placeCoin(position)){
            position = rand.nextInt(board.getNumColumns());
        }

        return position;
    }
}
