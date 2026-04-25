package connect4;

import connect4.players.Player;
import connect4.players.PlayerFactory;
import connect4.strategy.UserStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    public Board createEmptyDefaultBoard(){
        Board.getInstance().clearBoard();
        return Board.getInstance();
    }

    public Board createBoardWithFullColumn(){
        Board board = Board.getInstance();
        int p = 0;

        for(int i = 0; i < board.getNumRows(); i++){
            board.updateBoard(i, 0, ( (p % 2) + 1 ) );
            p++;
        }
        return board;
    }

    @Test
    public void testUserConstructor(){
        Connect4 game = new Connect4();
        Player user = PlayerFactory.createUser(1, game);
        assertTrue(user.isUser());
        assertEquals(1, user.getPlayer());
        assertEquals(game, user.getGame());
        assertInstanceOf(UserStrategy.class, user.getStrategy());
    }

    @Test
    public void testRandomBotConstructor(){
        Connect4 game = new Connect4();
        Player bot = PlayerFactory.createRandomBot(2, game);
        assertTrue(bot.isRandomBot());
        assertEquals(2, bot.getPlayer());
    }

    @Test
    public void testAIBotConstructor(){
        Connect4 game = new Connect4();
        Player bot = PlayerFactory.createAIBot(2, game);
        assertTrue(bot.isAIBot());
        assertEquals(2, bot.getPlayer());
    }

    @Test
    public void testPlaceCoinOnEmptyColumn(){
        Board board = createEmptyDefaultBoard();
        Connect4 game = new Connect4();
        Player p1 = PlayerFactory.createUser(1, game);

        boolean valid = p1.placeCoin(0);

        int[][] expectedBoard = createEmptyDefaultBoard().getBoard();
        expectedBoard[0][0] = 1;

        assertTrue(valid);
        assertEquals(board.getBoard(), expectedBoard);
    }

    @Test
    public void testPlaceCoinOnFullColumn(){
        Board board = createBoardWithFullColumn();
        Connect4 game = new Connect4();
        Player p1 = PlayerFactory.createUser(1, game);
        int[][] expectedBoard = board.getBoard();

        boolean valid = p1.placeCoin(0);

        assertFalse(valid);
        assertEquals(board.getBoard(), expectedBoard);
    }
}
