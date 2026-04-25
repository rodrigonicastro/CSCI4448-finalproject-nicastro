package connect4.state;

import connect4.Connect4;

public interface IState{
    public void executeState(Connect4 game);

    default boolean isInitState(){ return false; }
    default boolean isMenuState(){ return false; }
    default boolean isPlayGameState(){ return false; }
    default boolean isShowWinnerState(){ return false; }
    default boolean isResultsState(){ return false; }
    default boolean isEndState(){ return false; }
}