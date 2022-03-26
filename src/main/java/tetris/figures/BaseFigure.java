package tetris.figures;

import common.Publisher;
import tetris.common.Block;
import tetris.common.Coordinate;

public class BaseFigure extends Publisher implements Figure {
    private final Coordinate centre;
    private final Block[] blocks;

    public BaseFigure(Coordinate centre, Block[] blocks) { this.centre = centre; this.blocks = blocks; }

    @Override
    public void setPos(int x, int y) {
        int xDiff = x - (int)centre.getX();
        int yDiff = y - (int)centre.getY();
        shift(xDiff, yDiff);
    }

    @Override
    public void rotate() {
        for (final Block block : blocks) {
            block.rotateOnce(centre, true);
        }
    }

    @Override
    public void rotate(boolean directionClockwise) {
        for (final Block block : blocks) {
            block.rotateOnce(centre, directionClockwise);
        }
    }

    @Override
    public void shift(int x, int y) {
        for (final Block block : blocks) {
            block.shift(x, y);
        }
        centre.shift(x, y);
    }

    @Override
    public Block[] getBlocks() { return blocks; }
}
