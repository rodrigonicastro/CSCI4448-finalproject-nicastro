package connect4.state;

import connect4.Connect4;

public class ShowWinnerState implements IState{
    public void executeState(Connect4 game){
        game.getPanel().showWinnerMessage();
    }

    @Override
    public boolean isShowWinnerState(){ return true; }
}