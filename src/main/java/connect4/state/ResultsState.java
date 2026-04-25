package connect4.state;

import connect4.Connect4;

public class ResultsState implements IState{
    public void executeState(Connect4 game){
        game.getPanel().showResults();
    }

    @Override
    public boolean isResultsState(){ return true; }
}