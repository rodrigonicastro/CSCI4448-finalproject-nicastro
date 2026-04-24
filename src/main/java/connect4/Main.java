package connect4;

import connect4.players.Player;
import connect4.players.PlayerFactory;

public class Main {
    static void main() {
        PlayerFactory playerFactory = new PlayerFactory();
        Connect4 game = new Connect4();
        Connect4Panel panel = new Connect4Panel(game);

        Player p1 = playerFactory.createRandomBot(1, game);
        Player p2 = playerFactory.createRandomBot(2, game);

        game.addPlayers(p1, p2);
        game.addPanel(panel);

        game.connect4FiniteStateMachine();
    }
}
