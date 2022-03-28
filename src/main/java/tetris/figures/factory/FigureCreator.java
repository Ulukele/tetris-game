package tetris.figures.factory;

import tetris.common.BlockColor;
import tetris.figures.Figure;

public interface FigureCreator {
    Figure create(BlockColor color);
}
