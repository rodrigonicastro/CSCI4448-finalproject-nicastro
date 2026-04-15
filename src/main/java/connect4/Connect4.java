package connect4;

import connect4.players.Player;

import static connect4.GameState.*;

public class Connect4{
    private Player p1; //always User
    private Player p2; //decide between User, RandomBot, or AIBot
    private GameState gameState;

    private int turns;

    public Connect4(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        this.gameState = Init;
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
                case Init:
                    //initialize p1/p2, set strategies, initialize board, etc;
                    gameState = MENU;
                    break;
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
                    resetGame(p1, p2);
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
                    //show terminate message
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

    public boolean isBoardFull(){ return Board.getInstance().isFull(); }

    private void resetGame(Player p1, Player p2){
        p1.resetWon();
        p2.resetWon();

        Board.getInstance().clearBoard();
    }
}
