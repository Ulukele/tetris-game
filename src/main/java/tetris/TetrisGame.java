package tetris;

import tetris.figures.Figure;
import tetris.figures.factory.StaticFigureFactory;

public class TetrisGame {
    private final GameSpace gameSpace;
    private final Score score;
    private Figure activeFigure;

    public TetrisGame(GameSpace gameSpace, Score score) {
        this.gameSpace = gameSpace;
        this.score = score;
    }

    public void startNewGame() {
        gameSpace.clear();
        score.clear();
        createNewActiveFigure();
    }

    public void nextTick() {
        if ( checkActiveFigureFallen() ) {
            gameSpace.appendFigure(activeFigure);
            createNewActiveFigure();
        } else {
            makeActiveFigureFall();
        }
    }

    public Figure getActiveFigure() { return activeFigure; }

    public void askRotateActiveFigure() {
        rotateActiveFigure(true);
        if (gameSpace.checkFigureConflict(activeFigure)) rotateActiveFigure(false);
    }

    public void askShiftActiveFigure(int x, int y) throws NullPointerException {
        int xBack = -x;
        int yBack = -y;

        shiftActiveFigure(x, y);
        if (gameSpace.checkFigureConflict(activeFigure)) shiftActiveFigure(xBack, yBack);
    }

    private void createNewActiveFigure() {
        activeFigure = StaticFigureFactory.createRandom();
        // TODO: place figure correct
        activeFigure.setPos(gameSpace.getWidth() / 2, gameSpace.getHeight() - 5);
    }

    private void shiftActiveFigure(int x, int y) throws NullPointerException {
        if (activeFigure == null) throw new NullPointerException();
        activeFigure.shift(x, y);
    }

    private void rotateActiveFigure(boolean directionClockwise) throws NullPointerException {
        if (activeFigure == null) throw new NullPointerException();
        activeFigure.rotate(directionClockwise);
    }

    private void makeActiveFigureFall() throws NullPointerException {
        if (activeFigure == null) throw new NullPointerException();
        activeFigure.shift(0, -1);
    }

    private boolean checkActiveFigureFallen() throws NullPointerException {
        if (activeFigure == null) throw new NullPointerException();

        // if bottom of figure touches game space -- figure fallen
        // touching bottom means <=> if shift down it conflicts with game space
        activeFigure.shift(0, -1);
        boolean fallen = gameSpace.checkFigureConflict(activeFigure);
        activeFigure.shift(0, 1);

        return fallen;
    }

    public GameSpace getGameSpace() {
        return gameSpace;
    }

    public Score getScore() {
        return score;
    }
}
