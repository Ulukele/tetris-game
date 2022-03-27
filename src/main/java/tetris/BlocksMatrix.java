package tetris;

import org.jetbrains.annotations.NotNull;
import tetris.common.Block;

public class BlocksMatrix {
    private final int width;
    private final int height;
    private final boolean[][] bitMatrix;

    public BlocksMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        bitMatrix = new boolean[height][width];
        clear();
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
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                bitMatrix[i][j] = false;
            }
        }
    }

    public void set(int x, int y, boolean value) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        bitMatrix[y][x] = value;
    }

    public void set(int y, boolean value) throws IndexOutOfBoundsException {
        if (y > height || y < 0) throw new IndexOutOfBoundsException();
        for (int i = 0; i < width; ++i) bitMatrix[y][i] = value;
    }

    public void set(@NotNull Block block, boolean value) throws IndexOutOfBoundsException {
        set(block.getX(), block.getY(), value);
    }

    public boolean get(int x, int y) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        return bitMatrix[y][x];
    }

    public boolean get(@NotNull Block block) {
        return get(block.getX(), block.getY());
    }

    public boolean[][] getBitMatrix() {
        return bitMatrix;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
