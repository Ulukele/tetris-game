package tetris.figures;

import tetris.common.Point;

public class Figure {
    private Point centre;
    private final Point[] blocks;

    public Figure(Point[] blocks) { this.blocks = blocks; }
    public Figure(Point centre, Point[] blocks) { this.centre = centre; this.blocks = blocks; }

    public void rotate() {
        for (final Point block : blocks) {
            block.rotateOnce(centre, true);
        }
    }

    public void shift(int x, int y) {
        for (final Point block : blocks) {
            block.shift(x, y);
        }
        centre.shift(x, y);
    }

    public Point[] getBlocks() { return blocks; }
}
