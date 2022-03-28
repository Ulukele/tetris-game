package tetris;

import Exceptions.ActiveFigureException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for changing game state
 * - make actions (move, rotate)
 * - get game objects (...)
 */
public class TetrisEngine {
    private final GameSpace gameSpace;
    private final Score score;
    private final Timer timer = new Timer();
    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            nextTick();
        }
    };

    public TetrisEngine(GameSpace gameSpace, Score score) {
        this.gameSpace = gameSpace;
        this.score = score;
    }

    public void pause() {
        timer.cancel();
    }

    public void unpause() {
        timer.schedule(timerTask, 0, 500);
    }

    public void startNewGame() {
        score.clear();
        gameSpace.clear();

        timer.schedule(timerTask, 0, 500);
    }

    public void increaseScore(int deletedRows) {
        int scoreDelta;
        if (deletedRows >= 4) {
            scoreDelta = deletedRows * gameSpace.getWidth() * 2;
        } else {
            scoreDelta = deletedRows * gameSpace.getWidth();
        }
        score.increase(scoreDelta);
    }

    public void nextTick() {
        try {
            gameSpace.fallActiveFigure();
        } catch (ActiveFigureException exception) {
            pause();
        }
        int filledRows = gameSpace.deleteFilledRows();
        increaseScore(filledRows);
    }

    public void askShiftActiveFigure(int x, int y) {
        gameSpace.askShiftFigure(x, y);
    }

    public void askRotateActiveFigure() {
        gameSpace.askRotateFigure();
    }
}
