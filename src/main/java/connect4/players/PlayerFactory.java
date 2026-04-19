package connect4.players;

import connect4.Connect4;
import connect4.strategy.StrategyFactory;

public class PlayerFactory{
    static final StrategyFactory stratFac = new StrategyFactory();

    public static Player createUser(int player, Connect4 game){
        return new Player(player, stratFac.newUserStrategy(), game);
    }
    public static Player createRandomBot(int player, Connect4 game){
        return new Player(player, stratFac.newRandomBotStrategy(), game);
    }
    public static Player createAIBot(int player, Connect4 game){
        return new Player(player, stratFac.newAIBotStrategy(), game);
    }
}
