package tetris;

import common.Model;
import common.Publisher;
import exceptions.ActiveFigureException;
import exceptions.FactoryException;
import exceptions.FiguresFactoryConfigParsingException;
import tetris.common.Block;
import tetris.figures.Figure;
import tetris.figures.factory.FigureFactory;

/**
 * Class for figure management
 * it always contains not null figure
 */
public class ActiveFigure extends Publisher implements Model<Block[]> {
    private Figure activeFigure;
    private Figure nextFigure;
    private final FigureFactory figureFactory = new FigureFactory();

    public ActiveFigure() throws ActiveFigureException {
        try {
            figureFactory.configure();
        } catch (FiguresFactoryConfigParsingException | FactoryException exception) {
            throw new ActiveFigureException("Unable to load figures");
        }
        activeFigure = figureFactory.createRandom();
        nextFigure = figureFactory.createRandom();
    }

    public void changeActiveFigure() {
        activeFigure = nextFigure;
        nextFigure = figureFactory.createRandom();
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
