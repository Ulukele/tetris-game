package view;

import controller.Command;
import controller.TetrisClient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

    private final TetrisClient tetrisClient;

    public TetrisKeyListener(TetrisClient tetrisClient) {
        super();
        this.tetrisClient = tetrisClient;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ('a') -> tetrisClient.execute(Command.Left);
            case ('s') -> tetrisClient.execute(Command.Down);
            case ('d') -> tetrisClient.execute(Command.Right);
            case (' ') -> tetrisClient.execute(Command.Rotate);
            case ('\n') -> tetrisClient.execute(Command.Menu);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
