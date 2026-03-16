package Connect4.Players;

import Connect4.Board;
import Connect4.Color;

public class User extends Player{

    public User(Color color, Board board, int player){ super(color, board, player); }

    @Override
    public int determineMove(){
        //determine move through interface
        return 0;
    }
}
