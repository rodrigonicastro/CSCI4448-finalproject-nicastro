package connect4.state;

import connect4.Connect4;
import connect4.players.Player;

public class PlayGameState implements IState{
    public void executeState(Connect4 game){
        game.playGame();

        Player p1 = game.getP1();
        Player p2 = game.getP2();

        if(p1.hasConnected4()){
            p1.incrementWins();
            p2.incrementLosses();
        }
        else if(p2.hasConnected4()){
            p1.incrementLosses();
            p2.incrementWins();
        }
        else{
            p1.incrementDraws();
            p2.incrementDraws();
        }

        game.setGameState(new ShowWinnerState());
    }

    @Override
    public boolean isPlayGameState(){ return  true; }
}