package connect4.state;

import connect4.Connect4;

public class MenuState implements IState{
    public void executeState(Connect4 game){
        game.getPanel().displayMenu();
    }

    @Override
    public boolean isMenuState(){ return true; }
}