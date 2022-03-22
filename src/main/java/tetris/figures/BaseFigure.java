package tetris.figures;

import tetris.common.Block;

public class BaseFigure implements Figure {
    private Block centre;
    private final Block[] blocks;

    public BaseFigure(Block[] blocks) { this.blocks = blocks; }
    public BaseFigure(Block centre, Block[] blocks) { this.centre = centre; this.blocks = blocks; }

    @Override
    public void setPos(int x, int y) {
        int xDiff = centre.getX() - x;
        int yDiff = centre.getY() - y;
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
