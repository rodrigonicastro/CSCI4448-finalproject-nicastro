package connect4;

import connect4.players.Player;

import javax.swing.*;

import static connect4.GameState.*;

public class Connect4{
    private Player p1;
    private Player p2;
    private GameState gameState;
    public Connect4Panel panel;

    private int turns;

    public Connect4(){
        panel = new Connect4Panel(this);
        this.gameState = Init;
    }

    public Connect4 addPlayers(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
        return this;
    }

    public Connect4Panel getPanel(){ return panel; }

    public Player getP1(){ return p1; }
    public Player getP2(){ return p2; }

    public int getTurns(){ return turns; }

    public void resetTurns() { turns = 0; }

    public GameState getState(){ return gameState; }

    public void playGame(){
        while(!isOver()){
            if(turns % 2 == 0){
                if(p1.isAIBot() || p1.isRandomBot()) panel.showWaitingMessage();
                p1.playTurn();
            }
            else{
                if(p2.isAIBot() || p2.isRandomBot()) panel.showWaitingMessage();
                p2.playTurn();
            }
            turns++;
        }
    }

    public void setGameState(GameState state){ gameState = state; }

    public void connect4FiniteStateMachine(){
        boolean terminateGame = false;
        while(!terminateGame){
            switch(gameState){
                case Init:
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("Connect 4");

                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setSize(700, 650);
                        frame.setLocationRelativeTo(null);

                        frame.add(panel);
                        frame.setVisible(true);

                        panel.requestFocusInWindow();
                    });

                    gameState = MENU;
                    break;
                case MENU:
                    panel.displayMenu();
                    break;
                case PlayGame:
                    playGame();
                    if(p1.hasConnected4()){
                        p1.incrementWins();
                        p2.incrementLosses();
                    }
                    else if(p2.hasConnected4()){
                        p1.incrementLosses();
                        p2.incrementWins();
                    }
                    else{
                        p1.incrementDraws();
                        p2.incrementDraws();
                    }
                    gameState = ShowWinner;
                    break;
                case ShowWinner:
                    panel.showWinnerMessage();
                    break;
                case Results:
                    panel.showResults();
                    break;
                case End:
                    terminateGame = true;
                    System.exit(0);
                    break;
            }
        }
    }

    public boolean isOver(){
        //game ends when p1 or p2 gets 4 in a row or board is full
        //increment win count for winner, loss count for loser, or draw counter for both
        //post winner message (observer?)
        return isBoardFull() || p1.hasConnected4() || p2.hasConnected4();
    }

    public boolean isBoardFull(){ return Board.getInstance().isFull(); }

    public void resetGame(Player p1, Player p2){
        p1.resetWon();
        p2.resetWon();

        turns = 0;
        Board.getInstance().clearBoard();
    }
}
