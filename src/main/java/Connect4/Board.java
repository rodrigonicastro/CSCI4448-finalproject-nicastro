package Connect4;

public class Board{
    //TRANSFORM BOARD INTO SINGLETON?
    //Board is a 2d array where 0 means a free spot, 1 means player 1 has a coin in that spot, and 2 means player 2 has a coin in that spot
    int FREE = 0;
    int PLAYER1 = 1;
    int PLAYER2 = 2;

    private int[][] boardMatrix;
    private int rows;
    private int columns;

    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        boardMatrix = new int[rows][columns];
    }

    public int[][] getBoard(){ return boardMatrix; }

    public void updateBoard(int row, int column, int player){
        boardMatrix[row][column] = player;
    }

    public void clearBoard(){ //sets all slots to 0 to prepare for new game
        for(int[] col : boardMatrix){
            for(int slot : col) slot = 0;
        }
    }

    public int getNumRows(){ return rows; }
    public int getNumColumns(){ return columns; }

    public void displayBoard(){
        
    }
}
