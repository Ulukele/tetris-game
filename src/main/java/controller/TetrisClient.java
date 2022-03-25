package controller;

import tetris.TetrisGame;

public class TetrisClient implements IClient {

    private final TetrisGame tetrisGame;

    public TetrisClient(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    @Override
    public void execute(Command command) {
        if (command == Command.Down) {
            tetrisGame.askShiftActiveFigure(0, -1);
        }  else if (command == Command.Left) {
            tetrisGame.askShiftActiveFigure(-1, 0);
        } else if (command == Command.Right) {
            tetrisGame.askShiftActiveFigure(1, 0);
        } else if (command == Command.Rotate) {
            tetrisGame.askRotateActiveFigure();
        }

    }
}
