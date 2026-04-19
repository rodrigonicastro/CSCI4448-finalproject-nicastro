package connect4.strategy;

import connect4.Board;
import connect4.Connect4Panel;
import connect4.players.Player;

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