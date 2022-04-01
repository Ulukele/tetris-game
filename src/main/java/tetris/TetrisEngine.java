package tetris;

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
        timer.schedule(timerTask, 0, 500);
    }

    public void pause() {
        state.setState(GameStates.Pause);
    }

    public void unpause() {
        state.setState(GameStates.Playing);
    }

    public void switchState() {
        if (state.getData() == GameStates.Pause) {
            state.setState(GameStates.Playing);
        } else if (state.getData() == GameStates.Playing) {
            state.setState(GameStates.Pause);
        }
    }

    public void startNewGame() {
        score.clear();
        gameSpace.clear();
        state.setState(GameStates.Playing);
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
        if (state.getData() != GameStates.Playing) return;
        boolean success = gameSpace.tryToFallActiveFigure();
        if (!success) {
            state.setState(GameStates.Lose);
            pause();
            return;
        }

        int filledRows = gameSpace.deleteFilledRows();
        increaseScore(filledRows);
    }

    public void askShiftActiveFigure(int x, int y) {
        if (state.getData() != GameStates.Playing) return;
        gameSpace.askShiftFigure(x, y);
    }

    public void askRotateActiveFigure() {
        if (state.getData() != GameStates.Playing) return;
        gameSpace.askRotateFigure();
    }
}
