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
        } else if (command == Command.Stop) {
            tetrisEngine.pause();
        } else if (command == Command.Continue) {
            tetrisEngine.unpause();
        } else if (command == Command.NewGame) {
            tetrisEngine.startNewGame();
        } else if (command == Command.SwitchStopContinue) {
            tetrisEngine.switchState();
        } else if (command == Command.About) {
            tetrisEngine.setAboutInfoState();
        } else if (command == Command.HighScores) {
            tetrisEngine.setHighScoresState();
        }
    }
}
