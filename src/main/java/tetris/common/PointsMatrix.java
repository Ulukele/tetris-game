package tetris.common;

import java.util.BitSet;

public class PointsMatrix {
    private final int width;
    private final int height;
    private final BitSet bitSet;

    public PointsMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.bitSet = new BitSet(width * height);
    }

    public void shiftDown(int shiftValue) throws IndexOutOfBoundsException {
        if (shiftValue > height) throw new IndexOutOfBoundsException();

        int top = height - shiftValue;
        // Shift from [height, shiftValue] to [top, 0]
        for (int y = 0; y < top; ++y) {
            for (int x = 0; x < width; ++x) {
                boolean value = get(x, y + shiftValue);
                set(x, y, value);
            }
        }
        // Clear [height, top]
        for (int y = top; y < height; ++y) {
            set(y, false);
        }
    }

    public boolean rowIsFull(int y) {
        for (int x = 0; x < width; ++x) {
            if (!get(x, y)) return false;
        }
        return true;
    }

    public void clear() {
        bitSet.clear();
    }

    public void set(int x, int y, boolean value) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        int index = y * width + x;
        bitSet.set(index, value);
    }

    public void set(int y, boolean value) throws IndexOutOfBoundsException {
        if (y > height || y < 0) throw new IndexOutOfBoundsException();
        int left = y * width;
        int right = (y + 1) * width - 1;
        bitSet.set(left, right, value);
    }

    public void set(Point point, boolean value) throws IndexOutOfBoundsException {
        set(point.getX(), point.getY(), value);
    }

    public boolean get(int x, int y) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        return bitSet.get(y * width + x);
    }

    public boolean get(Point point) {
        return get(point.getX(), point.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
