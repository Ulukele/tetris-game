package controller;

import tetris.TetrisEngine;

public class TetrisClient implements IClient {

    private final TetrisEngine tetrisEngine;

    public TetrisClient(TetrisEngine tetrisEngine) {
        this.tetrisEngine = tetrisEngine;
    }

    @Override
    public void execute(Command command) {
        if (command == Command.Down) {
            tetrisEngine.askShiftActiveFigure(0, -1);
        } else if (command == Command.Left) {
            tetrisEngine.askShiftActiveFigure(-1, 0);
        } else if (command == Command.Right) {
            tetrisEngine.askShiftActiveFigure(1, 0);
        } else if (command == Command.Rotate) {
            tetrisEngine.askRotateActiveFigure();
        } else if (command == Command.Menu) {
            tetrisEngine.pause();
        }
    }
}
