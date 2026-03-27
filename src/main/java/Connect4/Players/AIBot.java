package Connect4.Players;
import Connect4.Board;
import Connect4.Color;

public class AIBot extends Player{
    public AIBot(Color color, Board board, int player){ super(color, board, player); }

    @Override
    public int determineMove(){
        int defenseRating = getDefenseRating();
        int attackRating = getAttackRating();

        //AI should only defend if P1 is about to win, attack otherwise
        if(defenseRating >= 3 && attackRating < 3) return getDefensePosition();
        else return getAttackPosition();
    }

    private int getDefensePosition(){
        //get the best defense position (the column where a coin placed would stop P1's biggest streak)
        return 0;
    }

    private int getAttackPosition(){
        //get the best attack position (the column where a coin placed would increase P2's biggest streak)
        return 0;
    }

    private int getDefenseRating(){
        //analyze board to determine how effective a defense would be (how many coins P1 has in a row)
        Board gameBoard = Board.getInstance();
        int biggestRating = 0;

        for(int i = 0; i < gameBoard.getNumColumns(); i ++){
            int verticalRating = getVerticalRatingForColumn(i, getPlayer());
            int horizontalRating = getHorizontalRatingForColumn(i, getPlayer());
            int diagonalRating = getDiagonalRatingForColumn(i, getPlayer());

            biggestRating = Math.max(biggestRating,
                            Math.max(verticalRating,
                            Math.max(horizontalRating,
                                     diagonalRating)));
        }
        return 0;
    }

    private int getAttackRating(){
        //analyze board to determine how effective an attack would be(how many coins P2 has in a row)
        return 0;
    }

    private int getVerticalRatingForColumn(int column, int player){
        Board gameBoard = Board.getInstance();
        int rating = 0;

        //find in which row the coin would land in this column
        int landingRow = -1;
        for(int i = 0; i < gameBoard.getNumRows(); i++){
            if(gameBoard.getBoard()[i][column] == 0){
                landingRow = i;
                break;
            }
        }

        if(landingRow <= 0) return rating; //-1 means column is full, 0 means column is empty. On either case, rating is 0

        //look down starting from where the coin would land in this column and count how many of this player's coins are there
        for(int i = landingRow-1; i >= 0; i--){
            if(gameBoard.getBoard()[landingRow][column] == player) rating++;
            else break;
        }

        return rating;
    }

    private int getHorizontalRatingForColumn(int column, int player){

    }

    private int getDiagonalRatingForColumn(int column, int player){

    }
}
