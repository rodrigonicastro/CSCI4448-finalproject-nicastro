package Connect4.Players;

import Connect4.Board;
import Connect4.Color;

import static Connect4.Color.BLUE;
import static Connect4.Color.RED;

public class PlayerFactory{
    static final int PLAYER1 = 1;
    static final int PLAYER2 = 2;

    static final Color DEFAULT_PLAYER1_COLOR = RED;
    static final Color DEFAULT_PLAYER2_COLOR = BLUE;

    public static Player createUser(Color color, Board board, int player){ return new User(color, board, player); }
    public static Player createRandomBot(Color color, Board board){ return new RandomBot(color, board, PLAYER2); }
    public static Player createAIBot(Color color, Board board){ return new AIBot(color, board, PLAYER2); }
}
