package connect4.stepdefs;

import connect4.Board;
import connect4.Connect4;
import connect4.players.Player;
import connect4.players.PlayerFactory;
import connect4.strategy.AIBotStrategy;
import connect4.strategy.RandomBotStrategy;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardStepDefs {
    StepsState state = StepsState.getInstance();
    Player p1 = state.p1;
    Player p2 = state.p2;

    Connect4 game = state.game;

    Board board = state.board;

    @Given("an empty board")
    public void anEmptyBoard(){
        Board emptyBoard = Board.getInstance();
        emptyBoard.clearBoard();

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @Given("an almost full board")
    public void anAlmostFullBoard(){
        boolean p1Start = false;
        Board.getInstance().clearBoard();

        for(int i = 0; i < board.getNumColumns(); i++){
            if(i % 3 == 0) p1Start = !p1Start;
            for(int j = 0; j < board.getNumRows(); j++){
                if(j % 2 == 0){
                    if(p1Start) p1.placeCoin(i);
                    else p2.placeCoin(i);
                }
                else{
                    if(!p1Start) p1.placeCoin(i);
                    else p2.placeCoin(i);
                }
            }
        }
        Board.getInstance().updateBoard(board.getNumRows()-1, 0, 0);
        state.updateState(p1, p2, board.getBoard(), game);
    }

    @Given("column {int} is full")
    public void giveFullColumn(int col){
        for(int i = 0; i < Board.getInstance().getNumRows(); i=i+2){
            p1.placeCoin(col);
            p2.placeCoin(col);
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @And("Player {int} has 3 coins in a row vertically")
    public void playerHas3CoinsInARowVertically(int player){
        if(player == 1){
            p1.placeCoin(3);
            p1.placeCoin(3);
            p1.placeCoin(3);
        }
        else{
            p2.placeCoin(3);
            p2.placeCoin(3);
            p2.placeCoin(3);
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @And("Player {int} has 3 coins in a row horizontally")
    public void playerHas3CoinsInARowHorizontally(int player){
        if(player == 1){
            p1.placeCoin(2);
            p1.placeCoin(3);
            p1.placeCoin(4);
        }
        else{
            p2.placeCoin(2);
            p2.placeCoin(3);
            p2.placeCoin(4);
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @And("Player {int} has 3 coins in a row diagonally from bottom-left to top-right")
    public void playerHas3CoinsInARowDiagonallyFromBottomLeftToTopRight(int player){
        if(player == 1){
            p1.placeCoin(1);

            p2.placeCoin(2);
            p1.placeCoin(2);

            p2.placeCoin(3);
            p2.placeCoin(3);
            p1.placeCoin(3);

            p2.placeCoin(4);
            p1.placeCoin(4);
            p2.placeCoin(4);
        }
        else{
            p2.placeCoin(1);

            p1.placeCoin(2);
            p2.placeCoin(2);

            p1.placeCoin(3);
            p1.placeCoin(3);
            p2.placeCoin(3);

            p1.placeCoin(4);
            p2.placeCoin(4);
            p1.placeCoin(4);
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @And("Player {int} has 3 coins in a row diagonally from top-left to bottom-right")
    public void playerHas3CoinsInARowDiagonallyFromTopLeftToBottomRight(int player){
        if(player == 1){
            p1.placeCoin(4);

            p2.placeCoin(3);
            p1.placeCoin(3);

            p2.placeCoin(2);
            p2.placeCoin(2);
            p1.placeCoin(2);

            p2.placeCoin(1);
            p1.placeCoin(1);
            p2.placeCoin(1);
        }
        else{
            p2.placeCoin(4);

            p1.placeCoin(3);
            p2.placeCoin(3);

            p1.placeCoin(2);
            p1.placeCoin(2);
            p2.placeCoin(2);

            p1.placeCoin(1);
            p2.placeCoin(1);
            p1.placeCoin(1);
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @Then("the game is over")
    public void gameIsOver(){
        assertTrue(game.isOver());
    }
}
