package view;

import controller.Command;
import controller.IClient;

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
            case ('w') -> tetrisClient.execute(Command.Rotate);
            case ('a') -> tetrisClient.execute(Command.Left);
            case ('s') -> tetrisClient.execute(Command.Down);
            case ('d') -> tetrisClient.execute(Command.Right);
            case ('r') -> tetrisClient.execute(Command.NewGame);
            case('\n') -> tetrisClient.execute(Command.SwitchStopContinue);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
