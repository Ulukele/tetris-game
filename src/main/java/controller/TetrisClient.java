package controller;

import Exceptions.UnknownCommand;
import tetris.TetrisGame;

public class TetrisClient implements IClient {

    private TetrisGame tetrisGame;

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
