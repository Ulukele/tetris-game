package tetris;

import tetris.highScores.UserScore;
import tetris.highScores.UsersScoresTable;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for changing game state
 * - make actions (move, rotate)
 * - get game objects (...)
 */
public class TetrisEngine {
    private final GameSpace gameSpace;
    private final ScoreCounter scoreCounter;
    private final GameState state;

    public TetrisEngine(
            GameSpace gameSpace,
            ScoreCounter scoreCounter,
            GameState gameState
    ) {
        this.gameSpace = gameSpace;
        this.scoreCounter = scoreCounter;
        this.state = gameState;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                nextTick();
            }
        };
        timer.schedule(timerTask, 0, 500);
    }

    public void pause() {
        state.setState(GameStates.Pause);
    }

    public void unpause() {
        state.setState(GameStates.Playing);
    }

    public void switchState() {
        if (state.getData() == GameStates.Playing) {
            state.setState(GameStates.Pause);
        } else {
            state.setState(GameStates.Playing);
        }
    }

    public void startNewGame() {
        scoreCounter.clear();
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
        scoreCounter.increase(scoreDelta);
    }

    public void nextTick() {
        if (state.getData() != GameStates.Playing) return;
        boolean success = gameSpace.tryToFallActiveFigure();
        if (!success) {
            state.setState(GameStates.Lose);
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

    public void setAboutInfoState() {
        state.setState(GameStates.AboutInfo);
    }
}
