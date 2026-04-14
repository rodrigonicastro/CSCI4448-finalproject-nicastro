package Connect4.Strategy;

import Connect4.Board;
import Connect4.Players.Player;
import Connect4.Strategy.IStrategy;
import java.util.Random;


public class RandomBotStrategy implements IStrategy{
    private final Random rand = new Random();

    public int determineMove(Player myself){
        int position = rand.nextInt(Board.getInstance().getNumColumns());

        while(!Board.getInstance().isColumnFull(position)){
            position = rand.nextInt(Board.getInstance().getNumColumns());
        }
        return position;
    }

    @Override
    public boolean isRandomBot() { return true; }
}