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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardStepDefs {
    Player p1 = PlayerFactory.createRandomBot(1);
    Player p2 = PlayerFactory.createRandomBot(2);

    Connect4 game = new Connect4(p1, p2);

    @Given("an empty board")
    public void anEmptyBoard(){
        Board emptyBoard = Board.getInstance();
        emptyBoard.clearBoard();
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
    }

    @When("Player {int} plays a turn")
    public void playerPlaysTurn(int player){
        if(player == 1){
            p1.playTurn();
        }
        else{
            p2.playTurn();
        }
    }

    @Then("the game is over")
    public void gameIsOver(){
        assertTrue(game.isOver());
    }

    @And("Player {int} won the game")
    public void playerWonTheGame(int player){
        if(player == 1){
            assertTrue(p1.hasConnected4());
        }
        else{
            assertTrue(p2.hasConnected4());
        }
    }
}
