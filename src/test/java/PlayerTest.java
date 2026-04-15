import connect4.Board;
import connect4.Color;
import connect4.players.Player;
import connect4.players.PlayerFactory;
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

    }

    @Test
    public void testRandomBotConstructor(){

    }

    @Test
    public void testAIBotConstructor(){

    }

    @Test
    public void testPlaceCoinOnEmptyColumn(){
        Board board = createEmptyDefaultBoard();
        Player p1 = PlayerFactory.createUser(1);

        boolean valid = p1.placeCoin(0);

        int[][] expectedBoard = createEmptyDefaultBoard().getBoard();
        expectedBoard[0][0] = 1;

        assertTrue(valid);
        assertEquals(board.getBoard(), expectedBoard);
    }

    @Test
    public void testPlaceCoinOnFullColumn(){
        Board board = createBoardWithFullColumn();
        Player p1 = PlayerFactory.createUser(1);
        int[][] expectedBoard = board.getBoard();

        boolean valid = p1.placeCoin(0);

        assertFalse(valid);
        assertEquals(board.getBoard(), expectedBoard);
    }
}
