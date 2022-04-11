package view;

import controller.MovementCommand;
import controller.IClient;
import controller.StateCommand;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

    private final IClient tetrisClient;

    public TetrisKeyListener(IClient tetrisClient) {
        super();
        this.tetrisClient = tetrisClient;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ('w') -> tetrisClient.execute(MovementCommand.Rotate);
            case ('a') -> tetrisClient.execute(MovementCommand.Left);
            case ('s') -> tetrisClient.execute(MovementCommand.Down);
            case ('d') -> tetrisClient.execute(MovementCommand.Right);
            case ('r') -> tetrisClient.execute(StateCommand.NewGame);
            case('\n') -> tetrisClient.execute(StateCommand.SwitchStopContinue);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
