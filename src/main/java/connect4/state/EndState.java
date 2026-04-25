package connect4.state;

import connect4.Connect4;

public class EndState implements IState{
    public void executeState(Connect4 game){
        System.exit(0);
    }

    @Override
    public boolean isEndState(){ return true; }
}