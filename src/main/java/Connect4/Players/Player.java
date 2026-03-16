package Connect4.Players;

import Connect4.Board;
import Connect4.Color;
import static Connect4.Color.BLUE;
import static Connect4.Color.RED;

abstract public class Player {
    Board board;
    int num_wins;
    int num_draws;
    int num_losses;
    Color color;
    int player;

    public Player(Color color, Board board, int player){
        this.num_draws = 0;
        this.num_wins = 0;
        this.num_losses = 0;
        this.color = color;
        this.board = board;
        this.player = player;
    }

    abstract int determineMove();

    public boolean placeCoin(int position){ //return true if valid placement, false otherwise
        for(int i = board.getNumRows(); i >= 0; i--){
            if(board.getBoard()[i][position] == 0){
                board.updateBoard(i, position, player);
                return true;
            }
        }
        return false;
    }

    public int getWins(){ return num_wins; }
    public int getDraws(){ return num_draws; }
    public int getLosses(){ return num_losses; }

    public void incrementWins() { num_wins++; }
    public void incrementDraws() { num_draws++; }
    public void incrementLosses() { num_losses++; }

    public void playTurn(){
        placeCoin(determineMove());
    }
}
