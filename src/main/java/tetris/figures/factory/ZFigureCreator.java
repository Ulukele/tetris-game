package tetris.figures.factory;

import tetris.common.Block;
import tetris.common.Coordinate;
import tetris.figures.BaseFigure;
import tetris.figures.Figure;

public class ZFigureCreator implements FigureCreator {
    @Override
    public Figure create() {
        Block[] blocks = {
                new Block(0, 1),
                new Block(1, 1),
                new Block(1, 0),
                new Block(2, 0)
        };
        Coordinate centre = new Coordinate(1.0f, 1.0f);
        return new BaseFigure(centre, blocks);
    }
}
