package tetris.figures.factory;

import tetris.common.Block;
import tetris.common.Coordinate;
import tetris.figures.BaseFigure;
import tetris.figures.Figure;

public class OFigureCreator implements FigureCreator {

    @Override
    public Figure create() {
        Block[] blocks = {
                new Block(0, 0),
                new Block(1, 0),
                new Block(0, 1),
                new Block(1, 1)
        };
        Coordinate centre = new Coordinate(0.5f, 0.5f);
        return new BaseFigure(centre, blocks);
    }
}
