package tetris.common;

public class Block {
    private int x;
    private int y;
    private BlockColor color;

    public Block() { x = 0; y = 0; }
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void shift(int xShift, int yShift) {
        x += xShift;
        y += yShift;
    }

    /**
     * Rotates point as if it is a part of
     * some bigger object
     * with centre in some block
     *
     * @param centre -- the block relative to which the rotation occurs.
     * @param directionClockwise -- clockwise (true) or counterclockwise (false).
     */
    public void rotateOnce(Block centre, boolean directionClockwise) {
        int xDiff = centre.getX() - x;
        int yDiff = centre.getY() - y;

        if (directionClockwise) {
            x = centre.getX() + yDiff;
            y = centre.getY() - xDiff;
        } else {
            x = centre.getX() - yDiff;
            y = centre.getY() + xDiff;
        }
    }

    /**
     * Rotates point as if it is a part of
     * some bigger object
     * with centre in some coordinate
     *
     * @param centre -- the coordinate relative to which the rotation occurs.
     * @param directionClockwise -- clockwise (true) or counterclockwise (false).
     */
    public void rotateOnce(Coordinate centre, boolean directionClockwise) {
        float xDiff = centre.getX() - x;
        float yDiff = centre.getY() - y;
        if (directionClockwise) {
            x = (int)(centre.getX() + yDiff);
            y = (int)(centre.getY() - xDiff);
        } else {
            x = (int)(centre.getX() - yDiff);
            y = (int)(centre.getY() + xDiff);
        }
    }

    public void setColor(BlockColor color) {
        this.color = color;
    }

    public BlockColor getColor() {
        return color;
    }

}
