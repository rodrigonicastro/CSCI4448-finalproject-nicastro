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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlayerStepDefs {
    StepsState state = StepsState.getInstance();

    Player p1 = state.p1;
    Player p2 = state.p2;

    Connect4 game = state.game;

    Board board = state.board;
    int[][] stateMatrix = state.stateMatrix;


    @When("player places a coin on column {int}")
    public void playerPlacesCoinOnColumn(int col){
        state.updateValidMove(p1.placeCoin(col));
    }

    @Then("the move is invalid")
    public void moveIsInvalid(){
        assertFalse(state.validMove);
    }

    @And("the board stays the same")
    public void boardStaysTheSame(){
        assertTrue(board.getBoard() == stateMatrix);
    }

    @And("Player {int} won the game")
    public void playerWonTheGame(int player){
        if(player == 1){
            assertTrue(p1.hasConnected4());
        }
        else{
            assertTrue(p2.hasConnected4());
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @And("Player {int} did not win the game")
    public void playerDidNotWinTheGame(int player){
        if(player == 1){
            assertFalse(p1.hasConnected4());
        }
        else{
            assertFalse(p2.hasConnected4());
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }

    @When("Player {int} plays a turn")
    public void playerPlaysTurn(int player){
        if(player == 1){
            p1.playTurn();
        }
        else{
            p2.playTurn();
        }
        state.updateState(p1, p1, board.getBoard(), game);
    }

    @And("Player {int} is a {word}")
    public void playerIsPlayerType(int player, String type){
        if(player == 1){
            switch(type){
                case "AIBot" -> p1.setStrategy(new AIBotStrategy());
                case "RandomBot" -> p1.setStrategy(new RandomBotStrategy());
            }
        }
        else{
            switch(type){
                case "AIBot" -> p2.setStrategy(new AIBotStrategy());
                case "RandomBot" -> p2.setStrategy(new RandomBotStrategy());
            }
        }

        state.updateState(p1, p2, board.getBoard(), game);
    }
}
