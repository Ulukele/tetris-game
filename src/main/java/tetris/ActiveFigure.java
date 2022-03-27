package tetris;

import tetris.figures.Figure;
import tetris.figures.factory.StaticFigureFactory;

/**
 * Class for figure management
 * it always contains not null figure
 */
public class ActiveFigure {
    private Figure activeFigure;

    public ActiveFigure() {
        activeFigure = StaticFigureFactory.createRandom();
    }

    public void changeActiveFigure() {
        activeFigure = StaticFigureFactory.createRandom();
    }

    public Figure getActiveFigure() {
        return activeFigure;
    }
}
