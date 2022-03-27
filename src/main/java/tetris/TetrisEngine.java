package tetris;

/**
 * Class for changing game state
 * - make actions (move, rotate)
 * - get game objects (...)
 */
public class TetrisEngine {
    private final GameSpace gameSpace;
    private final Score score;

    public TetrisEngine(GameSpace gameSpace, Score score) {
        this.gameSpace = gameSpace;
        this.score = score;
    }

    public void startNewGame() {
        score.clear();
        gameSpace.clear();
    }

    public void nextTick() {
        gameSpace.fallActiveFigure();
        int filledRows = gameSpace.deleteFilledRows();
        score.increase(filledRows * gameSpace.getWidth());
    }

    public void askShiftActiveFigure(int x, int y) {
        gameSpace.askShiftFigure(x, y);
    }

    public void askRotateActiveFigure() {
        gameSpace.askRotateFigure();
    }
}
