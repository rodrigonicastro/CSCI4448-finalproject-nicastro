package connect4;

import connect4.state.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {
    @Test
    public void testInitConstructor(){
        IState state = new InitState();
        assertTrue(state.isInitState());
    }

    @Test
    public void testMenuConstructor(){
        IState state = new MenuState();
        assertTrue(state.isMenuState());
    }

    @Test
    public void testPlayGameConstructor(){
        IState state = new PlayGameState();
        assertTrue(state.isPlayGameState());
    }

    @Test
    public void testShowWinnerConstructor(){
        IState state = new ShowWinnerState();
        assertTrue(state.isShowWinnerState());
    }

    @Test
    public void testResultsConstructor(){
        IState state = new ResultsState();
        assertTrue(state.isResultsState());
    }

    @Test
    public void testEndConstructor(){
        IState state = new EndState();
        assertTrue(state.isEndState());
    }

    @Test
    public void testDefaults(){
        IState state = new EndState();
        IState state2 = new InitState();
        assertFalse(state.isInitState());
        assertFalse(state.isMenuState());
        assertFalse(state.isPlayGameState());
        assertFalse(state.isShowWinnerState());
        assertFalse(state.isResultsState());
        assertFalse(state2.isEndState());
    }
}
