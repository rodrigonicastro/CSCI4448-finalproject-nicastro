package Connect4.Players;

import Connect4.Board;
import Connect4.Color;
import Connect4.Strategy.IStrategy;

import java.util.Random;

public class Player {
    private final Random rand = new Random();

    protected Board board;
    protected int num_wins;
    protected int num_draws;
    protected int num_losses;
    protected Color color;
    protected int player;
    protected boolean won;

    private IStrategy strategy;

    public Player(Color color, Board board, int player, IStrategy strategy){
        this.num_draws = 0;
        this.num_wins = 0;
        this.num_losses = 0;
        this.color = color;
        this.board = board;
        this.player = player;
        this.won = false;
        this.strategy = strategy;
    }

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

    protected IStrategy getStrategy() { return strategy; }

    public void setStrategy(IStrategy strategy) { this.strategy = strategy; }

    public int getWins(){ return num_wins; }
    public int getDraws(){ return num_draws; }
    public int getLosses(){ return num_losses; }
    public int getPlayer(){ return player; }

    public void incrementWins() { num_wins++; }
    public void incrementDraws() { num_draws++; }
    public void incrementLosses() { num_losses++; }

    public boolean hasConnected4(){ return won; }

    public void playTurn(){
        placeCoin(strategy.determineMove(this));
    }

    public boolean isWinningPlacement(int row, int col){
        return checkVerticalWin(row, col) || checkHorizontalWin(row, col) || checkDiagonalWin(row, col);
    }

    public void resetWon() { won = false; }

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

    public int getDefensePosition(){
        int opponent = getPlayer() == 1 ? 2 : 1;

        int bestDefensePosition = 0;

        for(int i = 0; i < board.getNumColumns(); i++){
            if(!board.isColumnFull(i)){
                if(getOverallRatingForColumn(i, opponent) > getOverallRatingForColumn(bestDefensePosition, opponent)){
                    bestDefensePosition = i;
                }
                else if(getOverallRatingForColumn(i, opponent) == getOverallRatingForColumn(bestDefensePosition, opponent)){
                    //if two placements are the same, decide randomly
                    if(rand.nextInt() % 2 == 0) bestDefensePosition = i;
                }
            }
        }
        return bestDefensePosition;
    }

    public int getAttackPosition(){
        int bestAttackPosition = 0;

        for(int i = 0; i < board.getNumColumns(); i++){
            if(!board.isColumnFull(i)){
                if(getOverallRatingForColumn(i, player) > getOverallRatingForColumn(bestAttackPosition, player)){
                    bestAttackPosition = i;
                }
                else if(getOverallRatingForColumn(i, player) == getOverallRatingForColumn(bestAttackPosition, player)){
                    //if two placements are the same, decide randomly
                    if(rand.nextInt() % 2 == 0) bestAttackPosition = i;
                }
            }
        }
        return bestAttackPosition;
    }

    public int getDefenseRating(){
        //analyze board to determine how effective a defense would be (how many coins P1 has in a row)
        int biggestRating = 0;

        int opponent = getPlayer() == 1 ? 2 : 1;

        for(int i = 0; i < board.getNumColumns(); i++){
            int verticalRating = getVerticalRatingForColumn(i, opponent);
            int horizontalRating = getHorizontalRatingForColumn(i, opponent);
            int diagonalRating = getDiagonalRatingForColumn(i, opponent);

            biggestRating = Math.max(biggestRating,
                            Math.max(verticalRating,
                            Math.max(horizontalRating,
                                    diagonalRating)));
        }
        return biggestRating;
    }

    public int getAttackRating(){
        int biggestRating = 0;

        for(int i = 0; i < board.getNumColumns(); i++){
            int verticalRating = getVerticalRatingForColumn(i, getPlayer());
            int horizontalRating = getHorizontalRatingForColumn(i, getPlayer());
            int diagonalRating = getDiagonalRatingForColumn(i, getPlayer());

            biggestRating = Math.max(biggestRating,
                            Math.max(verticalRating,
                            Math.max(horizontalRating,
                                    diagonalRating)));
        }
        return biggestRating;
    }

    public int getOverallRatingForColumn(int column, int player){
        return Math.max(getVerticalRatingForColumn(column, player),
                Math.max(getHorizontalRatingForColumn(column, player),
                getDiagonalRatingForColumn(column, player)));
    }

    public int getVerticalRatingForColumn(int column, int player){
        int rating = 0;

        int landingRow = findLandingRow(column);

        if(landingRow <= 0) return rating; //-1 means column is full, 0 means column is empty. On either case, rating is 0

        //look down starting from where the coin would land in this column and count how many of this player's coins are there
        for(int i = landingRow-1; i >= 0; i--){
            if(board.getBoard()[landingRow][column] == player) rating++;
            else break;
        }
        return rating;
    }

    public int getHorizontalRatingForColumn(int column, int player){
        int landingRow = findLandingRow(column);

        return getLeftRating(column, landingRow, player) + getRightRating(column, landingRow, player);
    }

    public int getLeftRating(int column, int row, int player){
        if(column == 0) return 0;

        int leftBorder = Math.max(0, column - 3);

        int rating = 0;
        for(int i = column - 1; i >= 0; i--){
            if(board.getBoard()[row][i] == player) rating++;
            else break;
        }
        return rating;
    }

    public int getRightRating(int column, int row, int player){
        if(column == board.getNumColumns() - 1) return 0;

        int rightBorder = Math.min(board.getNumColumns() - 1, column + 3);

        int rating = 0;
        for(int i = column; i <= rightBorder; i++){
            if(board.getBoard()[row][i] == player) rating++;
            else break;
        }
        return rating;
    }

    public int getDiagonalRatingForColumn(int column, int player){
        int landingRow = findLandingRow(column);

        return Math.max(getBottomLeftRating(column, landingRow, player)+getTopRightRating(column, landingRow, player),
                        getTopLeftRating(column, landingRow, player)+getBottomRightRating(column, landingRow, player));
    }

    public int getBottomLeftRating(int column, int row, int player){
        if(column == 0 || row == 0) return 0;

        int leftBorder = Math.max(0, column - 3);
        int bottomBorder = Math.max(0, row - 3);

        int currCol = column - 1;
        int currRow = row - 1;
        int rating = 0;
        while(currCol >= leftBorder && currRow >= bottomBorder){
            if(board.getBoard()[currRow][currCol] == player) rating++;
            else break;

            currCol--;
            currRow--;
        }

        return rating;
    }

    public int getTopRightRating(int column, int row, int player){
        if(column == board.getNumColumns() - 1 || row == board.getNumRows() - 1) return 0;

        int rightBorder = Math.min(board.getNumColumns() - 1, column + 3);
        int topBorder = Math.min(board.getNumRows() - 1, row + 3);

        int currCol = column + 1;
        int currRow = row + 1;
        int rating = 0;
        while(currCol <= rightBorder && currRow <= topBorder){
            if(board.getBoard()[currRow][currCol] == player) rating++;
            else break;

            currRow++;
            currCol++;
        }
        return rating;
    }

    public int getTopLeftRating(int column, int row, int player){
        if(column == 0 || row == board.getNumRows() - 1) return 0;

        int leftBorder = Math.max(0, column - 3);
        int topBorder = Math.min(board.getNumRows() - 1, row + 3);

        int currCol = column - 1;
        int currRow = row + 1;
        int rating = 0;
        while(currCol >= leftBorder && currRow <= topBorder){
            if(board.getBoard()[currRow][currCol] == player) rating++;
            else break;

            currCol--;
            currRow++;
        }
        return rating;
    }

    public int getBottomRightRating(int column, int row, int player){
        if(column == board.getNumColumns() - 1 || row == 0) return 0;

        int rightBorder = Math.min(board.getNumColumns() - 1, column + 3);
        int bottomBorder = Math.max(0, row - 3);

        int currCol = column + 1;
        int currRow = row - 1;
        int rating = 0;
        while(currCol <= rightBorder && currRow >= bottomBorder){
            if(board.getBoard()[currRow][currCol] == player) rating++;
            else break;

            currCol++;
            currRow--;
        }
        return rating;
    }

    public int findLandingRow(int column){
        //find in which row the coin would land in this column
        int landingRow = -1;
        for(int i = 0; i < board.getNumRows(); i++){
            if(board.getBoard()[i][column] == 0){
                landingRow = i;
                break;
            }
        }
        return landingRow;
    }
}
