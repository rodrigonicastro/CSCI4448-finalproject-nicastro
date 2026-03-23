package Connect4.Players;

import Connect4.Board;
import Connect4.Color;

abstract public class Player {
    protected Board board;
    protected int num_wins;
    protected int num_draws;
    protected int num_losses;
    protected Color color;
    protected int player;
    protected boolean won;

    public Player(Color color, Board board, int player){
        this.num_draws = 0;
        this.num_wins = 0;
        this.num_losses = 0;
        this.color = color;
        this.board = board;
        this.player = player;
        this.won = false;
    }

    abstract int determineMove();

    public boolean placeCoin(int column){ //return true if valid placement, false otherwise
        for(int i = board.getNumRows()-1; i >= 0; i--){
            if(board.getBoard()[i][column] == 0){
                board.updateBoard(i, column, player);
                if(isWinningPlacement(i, column)) this.won = true;
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

    public boolean hasConnected4(){ return won; }

    public void playTurn(){
        placeCoin(determineMove());
    }

    public boolean isWinningPlacement(int row, int col){
        return checkVerticalWin(row, col) || checkHorizontalWin(row, col) || checkDiagonalWin(row, col);
    }

    private boolean checkVerticalWin(int row, int col){
        int sequenceSize = 0;

        // VERTICAL CHECK: if the 3 coins below are this player's, won
        int verticalBorder = row - 3;
        if(verticalBorder >= 0){
            for(int currRow = row; currRow >= verticalBorder; currRow--){
                if(board.getBoard()[currRow][col] == this.player) sequenceSize++;
                else break;
            }
        }

        return sequenceSize >= 4;
    }

    private boolean checkHorizontalWin(int row, int col){
        //HORIZONTAL CHECK: take 3 to the left and 3 to the right. If a sequence of 4 is formed, won
        int leftBorder = Math.max(col - 3, 0);
        int rightBorder = Math.min(col + 3, board.getNumColumns() - 1);

        int sequenceSize = 0;
        for(int currCol = leftBorder; currCol <= rightBorder; currCol++){
            if(board.getBoard()[row][currCol] == this.player){
                sequenceSize++;
                if(sequenceSize >= 4) break;
            }
            else sequenceSize = 0;
        }

        return sequenceSize == 4;
    }

    private boolean checkDiagonalWin(int row, int col){
        return topLeftToBottomRightWin(row, col) || bottomLeftToTopRightWin(row, col);
    }

    private boolean topLeftToBottomRightWin(int row, int col){
        int topBorder = Math.min(row + 3, board.getNumRows() - 1);
        int bottomBorder = Math.max(col - 3, 0);
        int leftBorder = Math.max(col - 3, 0);
        int rightBorder = Math.min(col + 3, board.getNumColumns() - 1);

        //TOP-LEFT -> BOTTOM-RIGHT CHECK: take 3 to the top left and 3 to the bottom right. If a sequence of 4 is formed, won
        //start on placed coin, go to top left until hit end of board or no sequence. Then reset to placed coin and check bottom right
        int sequenceSize = 0;

        int currRow = row + 1;
        int currCol = col - 1;
        while(currRow <= topBorder && currCol >= leftBorder){
            if(board.getBoard()[currRow][currCol] == this.player){
                sequenceSize++;
                if(sequenceSize >= 4) break;
            }
            else break;

            currRow++;
            currCol--;
        }

        currRow = row - 1;
        currCol = col + 1;
        while(currRow >= bottomBorder && currCol <= rightBorder){
            if(board.getBoard()[currRow][currCol] == this.player){
                sequenceSize++;
                if(sequenceSize >= 4) break;
            }
            else break;

            currRow--;
            currCol++;
        }

        return sequenceSize == 4;
    }

    private boolean bottomLeftToTopRightWin(int row, int col){
        int topBorder = Math.min(row + 3, board.getNumRows() - 1);
        int bottomBorder = Math.max(col - 3, 0);
        int leftBorder = Math.max(col - 3, 0);
        int rightBorder = Math.min(col + 3, board.getNumColumns() - 1);

        //BOTTOM-LEFT -> TOP-RIGHT CHECK: take 3 to the bottom left and 3 to the top right. If a sequence of 4 is formed, won
        //start on placed coin, go to bottom left until hit end of board or no sequence. Then reset to placed coin and check top right
        int sequenceSize = 0;

        int currRow = row - 1;
        int currCol = col - 1;
        while(currRow >= bottomBorder && currCol >= leftBorder){
            if(board.getBoard()[currRow][currCol] == this.player){
                sequenceSize++;
                if(sequenceSize >= 4) break;
            }
            else break;

            currRow--;
            currCol--;
        }

        currRow = row + 1;
        currCol = col + 1;
        while(currRow <= topBorder && currCol <= rightBorder){
            if(board.getBoard()[currRow][currCol] == this.player){
                sequenceSize++;
                if(sequenceSize >= 4) break;
            }
            else break;

            currRow++;
            currCol++;
        }

        return sequenceSize == 4;
    }
}
