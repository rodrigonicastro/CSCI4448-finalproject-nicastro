package Connect4;

import Connect4.Players.Player;

public class Connect4{
    //timer?
    Player p1; //always User
    Player p2; //decide between User, RandomBot, or AIBot
    Board board;

    int turns;

    public Connect4(Board board, Player p1, Player p2){
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
    }

    public void playGame(){
        while(!isOver()){
            if(turns % 2 == 1) p1.playTurn();
            else p2.playTurn();
            turns++;
        }
    }

    public boolean isOver(){
        //game ends when red or blue get 4 in a row or board is full
        //increment win count for winner, loss count for loser, or draw counter for both
        //post winner message (observer?)
        return false;
    }
}
