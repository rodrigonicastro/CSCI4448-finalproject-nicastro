package Connect4;

import Connect4.Players.Player;

import static Connect4.GameState.*;

public class Connect4{
    private Player p1; //always User
    private Player p2; //decide between User, RandomBot, or AIBot
    private Board board;
    private GameState gameState;

    private int turns;

    public Connect4(Board board, Player p1, Player p2){
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        this.gameState = MENU;
    }

    public void playGame(){
        while(!isOver()){
            if(turns % 2 == 1) p1.playTurn();
            else p2.playTurn();
            turns++;
        }
    }

    public void connect4FiniteStateMachine(){
        boolean terminateGame = false;
        while(!terminateGame){
            switch(gameState){
                case MENU:
                    //prompt single/two player, config, terminate
                    break;
                case PlayGame:
                    playGame();

                    if(p1.hasConnected4()){
                        p1.incrementWins();
                        p2.incrementLosses();
                        //show winner message
                    }
                    else if(p2.hasConnected4()){
                        p1.incrementLosses();
                        p2.incrementWins();
                        //show winner message
                    }
                    else{
                        p1.incrementDraws();
                        p2.incrementDraws();
                        //show draw message
                    }
                    gameState = Results;
                    break;
                case Results:
                    //show results screen, prompt to terminate or go to menu
                    break;
                case Config:
                    //prompt choice of rng/AI/User for p2, maybe choose color
                    //still unsure if this will be implemented
                case End:
                    terminateGame = true;
                    break;
            }
        }
    }

    public boolean isOver(){
        //game ends when p1 or p2 gets 4 in a row or board is full
        //increment win count for winner, loss count for loser, or draw counter for both
        //post winner message (observer?)
        return isBoardFull() || p1.hasConnected4() || p2.hasConnected4();
    }

    public boolean isBoardFull(){ return board.isFull(); }
}
