package tetris.figures;

import tetris.common.Block;
import tetris.common.BlockColor;
import tetris.common.Coordinate;

public class BaseFigure implements Figure {
    private final Coordinate centre;
    private final Block[] blocks;

    public BaseFigure(Coordinate centre, Block[] blocks, BlockColor color) {
        this.centre = centre;
        this.blocks = blocks;
        for (final Block block : blocks) {
            block.setColor(color);
        }
    }

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
