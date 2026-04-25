package connect4.state;

import connect4.Connect4;
import connect4.Connect4Panel;

import javax.swing.*;

public class InitState implements IState{
    public void executeState(Connect4 game){
        Connect4Panel panel = game.getPanel();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Connect 4");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 650);
            frame.setLocationRelativeTo(null);

            frame.add(panel);
            frame.setVisible(true);

            panel.requestFocusInWindow();
        });

        game.setGameState(new MenuState());
    }

    @Override
    public boolean isInitState(){ return true; }
}