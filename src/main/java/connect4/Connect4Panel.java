package connect4;

import connect4.strategy.StrategyFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Connect4Panel extends JPanel {

    // --- Fields ---
    Board board = Board.getInstance();
    Connect4 game;

    private int selectedCol;
    private int activePlayer;
    private boolean waitingForMove;
    private boolean showWaitingMessage = false;
    private int chosenColumn;
    private boolean inMenu;
    private int menuIndex = 0;

    private Color PLAYER_1_COLOR = Color.BLUE;
    private Color PLAYER_2_COLOR = Color.RED;

    private String[] menuOptions = {
            "2-player mode",
            "Play against Random Moves",
            "Play against AI",
            "Exit game"
    };

    // --- Constructor ---
    public Connect4Panel(Connect4 game) {
        this.selectedCol = 0;
        this.activePlayer = 1;
        this.waitingForMove = false;
        this.chosenColumn = -1;
        this.game = game;

        setFocusable(true);
        setupKeyBindings();
    }

    // --- Rendering ---
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 80;
        int offsetX = 50;
        int offsetY = 100;
        int pieceSize = 60;

        if(inMenu){
            drawMenu(g);
            return;
        }

        if (showWaitingMessage) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Generating move", 50, 50);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            g.drawString("Generating move.", 50, 50);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            g.drawString("Generating move..", 50, 50);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            g.drawString("Generating move...", 50, 50);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (waitingForMove) {
            if (activePlayer == 1) {
                g.setColor(PLAYER_1_COLOR);
            } else if (activePlayer == 2) {
                g.setColor(PLAYER_2_COLOR);
            }

            int hoverX = offsetX + selectedCol * cellSize + 10;
            int hoverY = offsetY - 70;

            g.fillOval(hoverX, hoverY, pieceSize, pieceSize);
        }

        // Draw board background
        g.setColor(Color.BLACK);
        g.fillRect(offsetX, offsetY, 7 * cellSize, 6 * cellSize);

        // Draw cells
        for (int row = 0; row < board.getNumRows(); row++) {
            for (int col = 0; col < board.getNumColumns(); col++) {
                int x = offsetX + col * cellSize + 10;
                int y = offsetY + row * cellSize + 10;

                if (board.getBoard()[row][col] == 1) {
                    g.setColor(PLAYER_1_COLOR);
                } else if (board.getBoard()[row][col] == 2) {
                    g.setColor(PLAYER_2_COLOR);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillOval(x, y, pieceSize, pieceSize);
            }
        }
    }

    public void showWaitingMessage() {
        showWaitingMessage = true;
        repaint();

        try {
            Thread.sleep(500); // 0.5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        showWaitingMessage = false;
        repaint();
    }

    public void displayMenu(){
        inMenu = true;
        repaint();
    }

    private void drawMenu(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 28));

        int startY = 150;

        for (int i = 0; i < menuOptions.length; i++) {
            if (i == menuIndex) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString(menuOptions[i], 100, startY + i * 50);
        }
    }

    private void handleMenuSelection() {
        StrategyFactory strategyFactory = new StrategyFactory();
        switch (menuIndex) {
            case 0:
                game.getP1().setStrategy(strategyFactory.newUserStrategy());
                game.getP2().setStrategy(strategyFactory.newUserStrategy());
                game.setGameState(GameState.PlayGame);
                break;
            case 1:
                game.getP1().setStrategy(strategyFactory.newUserStrategy());
                game.getP2().setStrategy(strategyFactory.newRandomBotStrategy());
                game.setGameState(GameState.PlayGame);
                break;
            case 2:
                game.getP1().setStrategy(strategyFactory.newUserStrategy());
                game.getP2().setStrategy(strategyFactory.newAIBotStrategy());
                break;
            case 3:
                game.setGameState(GameState.End);
                break;
        }

        // Leave menu (you can adjust this later)
        inMenu = false;
        repaint();
    }


    // --- Input Handling ---
    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Player 1 - move left
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "p1Left");
        actionMap.put("p1Left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waitingForMove && activePlayer == 1 && selectedCol > 0) {
                    selectedCol--;
                    repaint();
                }
                else if(waitingForMove && activePlayer == 1 && selectedCol == 0){
                    selectedCol = board.getNumColumns()-1;
                    repaint();
                }
            }
        });

        // Player 1 - move right
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "p1Right");
        actionMap.put("p1Right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waitingForMove && activePlayer == 1 && selectedCol < board.getNumColumns()-1) {
                    selectedCol++;
                    repaint();
                }
                else if(waitingForMove && activePlayer == 1 && selectedCol == board.getNumColumns()-1){
                    selectedCol = 0;
                    repaint();
                }
            }
        });

        // Player 2 - move left
        inputMap.put(KeyStroke.getKeyStroke('A'), "p2Left");
        actionMap.put("p2Left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waitingForMove && activePlayer == 2 && selectedCol > 0) {
                    selectedCol--;
                    repaint();
                }
                else if(waitingForMove && activePlayer == 2 && selectedCol == 0){
                    selectedCol = board.getNumColumns()-1;
                    repaint();
                }
            }
        });

        // Player 2 - move right
        inputMap.put(KeyStroke.getKeyStroke('D'), "p2Right");
        actionMap.put("p2Right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waitingForMove && activePlayer == 2 && selectedCol < board.getNumColumns()-1) {
                    selectedCol++;
                    repaint();
                }
                else if(waitingForMove && activePlayer == 2 && selectedCol == board.getNumColumns()-1){
                    selectedCol = 0;
                    repaint();
                }
            }
        });

        // Both players - confirm move
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "select");
        actionMap.put("select", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (waitingForMove) {
                    chosenColumn = selectedCol;
                }
                if (inMenu) {
                    handleMenuSelection();
                }
            }
        });

        // UP
        inputMap.put(KeyStroke.getKeyStroke("UP"), "menuUp");
        actionMap.put("menuUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inMenu) {
                    menuIndex = (menuIndex - 1 + menuOptions.length) % menuOptions.length;
                    repaint();
                }
            }
        });

        // DOWN
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "menuDown");
        actionMap.put("menuDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inMenu) {
                    menuIndex = (menuIndex + 1) % menuOptions.length;
                    repaint();
                }
            }
        });
    }

    // --- Game Interaction ---
    public int getMoveFromPlayer(int player) {
        activePlayer = player;
        waitingForMove = true;
        selectedCol = 3;
        chosenColumn = -1;

        requestFocusInWindow();
        repaint();

        while (chosenColumn == -1) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        waitingForMove = false;
        return chosenColumn;
    }
}