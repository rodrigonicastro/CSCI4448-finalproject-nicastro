package connect4;

import connect4.players.Player;
import connect4.state.IState;
import connect4.state.InitState;

public class Connect4{
    private Player p1;
    private Player p2;
    public Connect4Panel panel;

    private IState currentState;

    private int turns;

    public Connect4(){
        currentState = new InitState();
    }

    public Connect4 addPlayers(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        return this;
    }

    public Connect4 addPanel(Connect4Panel panel){
        this.panel = panel;
        return this;
    }

    public Connect4Panel getPanel(){ return panel; }

    public Player getP1(){ return p1; }
    public Player getP2(){ return p2; }

    public int getTurns(){ return turns; }

    public void setGameState(IState state){ this.currentState = state; }

    public IState getCurrentState(){ return currentState; }

    public void playGame(){
        while(!isOver()){
            if(turns % 2 == 0){
                if(p1.isAIBot() || p1.isRandomBot()) panel.showWaitingMessage();
                p1.playTurn();
            }
            else{
                if(p2.isAIBot() || p2.isRandomBot()) panel.showWaitingMessage();
                p2.playTurn();
            }
            turns++;
        }
    }

    public void connect4FiniteStateMachine(){
        while(true){
            currentState.executeState(this);
        }
    }

    public boolean isOver(){
        //game ends when p1 or p2 gets 4 in a row or board is full
        //increment win count for winner, loss count for loser, or draw counter for both
        //post winner message (observer?)
        return isBoardFull() || p1.hasConnected4() || p2.hasConnected4();
    }

    public boolean isBoardFull(){ return Board.getInstance().isFull(); }

    public void resetGame(Player p1, Player p2){
        p1.resetWon();
        p2.resetWon();

        turns = 0;
        Board.getInstance().clearBoard();
    }
}
