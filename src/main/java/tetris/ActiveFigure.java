package tetris;

import common.IPublisher;
import common.Model;
import common.Publisher;
import tetris.common.Block;
import tetris.figures.Figure;
import tetris.figures.factory.StaticFigureFactory;

/**
 * Class for figure management
 * it always contains not null figure
 */
public class ActiveFigure extends Publisher implements Model<Block[]> {
    private Figure activeFigure;
    private Figure nextFigure;

    public ActiveFigure() {
        activeFigure = StaticFigureFactory.createRandom();
        nextFigure = StaticFigureFactory.createRandom();
    }

    public void changeActiveFigure() {
        activeFigure = nextFigure;
        nextFigure = StaticFigureFactory.createRandom();
        publishNotify();
    }

    public Figure getActiveFigure() {
        return activeFigure;
    }

    @Override
    public Block[] getData() {
        return nextFigure.getBlocks();
    }
}
