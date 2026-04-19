package connect4.stepdefs;

import connect4.Board;
import connect4.Connect4;
import connect4.players.Player;
import connect4.players.PlayerFactory;

public class StepsState {
    public Board board;
    public Player p1;
    public Player p2;
    public Connect4 game;
    public int[][] stateMatrix;
    public boolean validMove;


    private static StepsState state = null;

    private StepsState(){
        board = Board.getInstance();
        game = new Connect4();
        p1 = PlayerFactory.createRandomBot(1, game);
        p2 = PlayerFactory.createRandomBot(2, game);
        game.addPlayers(p1, p2);
        stateMatrix = board.getBoard();
    }

    public static StepsState getInstance(){
        if(state == null){
            state = new StepsState();
        }
        return state;
    }

    public void updateState(Player p1, Player p2, int[][] stateMatrix, Connect4 game){
        this.p1 = p1;
        this.p2 = p2;
        this.stateMatrix = stateMatrix;
        this.game = game;
    }

    public void updateValidMove(boolean valid){ validMove = valid; }
}
