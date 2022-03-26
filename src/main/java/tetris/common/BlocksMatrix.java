package tetris.common;

import common.Publisher;

import javax.swing.*;
import java.util.BitSet;

public class BlocksMatrix extends Publisher {
    private final int width;
    private final int height;
    private final BitSet bitSet;

    public BlocksMatrix(int width, int height) {
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
        // We need to change bitSet in UI thread
        // because BitSet not threadsafe
//        SwingUtilities.invokeLater(() -> {
            bitSet.set(index, value);
            publishNotify();
//        });
    }

    public void set(int y, boolean value) throws IndexOutOfBoundsException {
        if (y > height || y < 0) throw new IndexOutOfBoundsException();
        int left = y * width;
        int right = (y + 1) * width - 1;
        bitSet.set(left, right, value);
    }

    public void set(Block block, boolean value) throws IndexOutOfBoundsException {
        set(block.getX(), block.getY(), value);
    }

    public boolean get(int x, int y) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        return bitSet.get(y * width + x);
    }

    public boolean get(Block block) {
        return get(block.getX(), block.getY());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
