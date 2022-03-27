package tetris.figures;

import tetris.common.Block;

public interface Figure {
    void setPos(int x, int y);
    void rotate();
    void rotate(boolean directionClockwise);
    void shift(int x, int y);
    Block[] getBlocks();
}
