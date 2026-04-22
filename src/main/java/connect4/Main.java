package connect4;

import connect4.players.Player;
import connect4.players.PlayerFactory;
import connect4.strategy.StrategyFactory;

public class Main {
    static void main() {
        PlayerFactory playerFactory = new PlayerFactory();
        Connect4 game = new Connect4();

        Player p1 = playerFactory.createRandomBot(1, game);
        Player p2 = playerFactory.createRandomBot(2, game);

        game.addPlayers(p1, p2);

        game.connect4FiniteStateMachine();
    }
}
