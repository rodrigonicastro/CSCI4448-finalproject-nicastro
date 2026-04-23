package connect4;

public class Board{
    //Board is a 2d array where 0 means a free spot, 1 means player 1 has a coin in that spot, and 2 means player 2 has a coin in that spot
    int FREE = 0;
    int PLAYER1 = 1;
    int PLAYER2 = 2;

    private int DEFAULT_NUM_ROWS = 6;
    private int DEFAULT_NUM_COLS = 7;

    private int[][] boardMatrix;
    private int rows;
    private int columns;

    private static Board gameBoard = null;

    private Board(){
        this.rows = DEFAULT_NUM_ROWS;
        this.columns = DEFAULT_NUM_COLS;
        this.boardMatrix = new int[rows][columns];
    }

    private Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.boardMatrix = new int[rows][columns];
    }

    public static Board getInstance(){
        if(gameBoard == null) gameBoard = new Board();
        return gameBoard;
    }

    public int[][] getBoard(){ return boardMatrix; }

    public void updateBoard(int row, int column, int player){
        boardMatrix[row][column] = player;
    }

    public void clearBoard(){ //sets all slots to 0 to prepare for new game
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                boardMatrix[i][j] = 0;
            }
        }
    }

    public int getNumRows(){ return rows; }
    public int getNumColumns(){ return columns; }

    public void displayBoard(){
        //display the board to the UI
    }

    public boolean isFull(){
        for(int i = 0; i < getNumColumns(); i++){
            if(!isColumnFull(i)) return false;
        }
        return true;
    }

    public boolean isColumnFull(int col){
        for(int i = getNumRows()-1; i >= 0; i--){
            if(getBoard()[i][col] == 0){
                return false;
            }
        }
        return true;
    }
}
