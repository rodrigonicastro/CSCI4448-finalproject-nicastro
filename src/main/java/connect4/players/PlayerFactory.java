package connect4.players;

import connect4.Color;
import connect4.strategy.StrategyFactory;

import static connect4.Color.BLUE;
import static connect4.Color.RED;

public class PlayerFactory{
    static final int PLAYER1 = 1;
    static final int PLAYER2 = 2;

    static final Color DEFAULT_PLAYER1_COLOR = RED;
    static final Color DEFAULT_PLAYER2_COLOR = BLUE;

    static final StrategyFactory stratFac = new StrategyFactory();

    public static Player createUser(int player){
        return new Player(player, stratFac.newUserStrategy());
    }
    public static Player createRandomBot(int player){
        return new Player(player, stratFac.newRandomBotStrategy());
    }
    public static Player createAIBot(int player){
        return new Player(player, stratFac.newAIBotStrategy());
    }
}
