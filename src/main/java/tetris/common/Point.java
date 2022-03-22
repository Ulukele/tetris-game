package tetris.common;

public class Point {
    private int x;
    private int y;

    public Point() { x = 0; y = 0; }
    public Point(int x, int y) {
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
     * with centre in some point
     *
     * @param centre -- the point relative to which the rotation occurs.
     * @param directionClockwise -- clockwise (true) or counterclockwise (false).
     */
    public void rotateOnce(Point centre, boolean directionClockwise) {
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

}
