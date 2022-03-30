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
    private final GameState state;
    private final Timer timer = new Timer();
    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            nextTick();
        }
    };

    public TetrisEngine(GameSpace gameSpace, Score score, GameState gameState) {
        this.gameSpace = gameSpace;
        this.score = score;
        this.state = gameState;
    }

    public void pause() {
        state.setState(GameStates.Menu);
        timer.cancel();
    }

    public void unpause() {
        state.setState(GameStates.Playing);
        timer.schedule(timerTask, 0, 500);
    }

    public void startNewGame() {
        score.clear();
        gameSpace.clear();
        state.setState(GameStates.Playing);
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
            state.setState(GameStates.Lose);
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
