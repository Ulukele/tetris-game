package tetris.figures;

import common.IPublisher;
import tetris.common.Block;

public interface Figure extends IPublisher {
    void setPos(int x, int y);
    void rotate();
    void rotate(boolean directionClockwise);
    void shift(int x, int y);
    Block[] getBlocks();
}
