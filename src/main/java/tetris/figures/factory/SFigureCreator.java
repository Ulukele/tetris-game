package tetris.figures.factory;

import tetris.common.Block;
import tetris.common.BlockColor;
import tetris.common.Coordinate;
import tetris.figures.BaseFigure;
import tetris.figures.Figure;

public class SFigureCreator implements FigureCreator {
    @Override
    public Figure create(BlockColor color) {
        Block[] blocks = {
                new Block(0, 0),
                new Block(1, 0),
                new Block(1, 1),
                new Block(2, 1)
        };
        Coordinate centre = new Coordinate(1.0f, 0.0f);
        return new BaseFigure(centre, blocks, color);
    }
}
