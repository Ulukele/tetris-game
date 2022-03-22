package tetris.figures;

import tetris.common.Block;
import tetris.common.Coordinate;

public class BaseFigure implements Figure {
    private final Coordinate centre;
    private final Block[] blocks;

    public BaseFigure(Coordinate centre, Block[] blocks) { this.centre = centre; this.blocks = blocks; }

    @Override
    public void setPos(int x, int y) {
        int xDiff = (int)centre.getX() - x;
        int yDiff = (int)centre.getY() - y;
        shift(xDiff, yDiff);
    }

    @Override
    public void rotate() {
        for (final Block block : blocks) {
            block.rotateOnce(centre, true);
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
