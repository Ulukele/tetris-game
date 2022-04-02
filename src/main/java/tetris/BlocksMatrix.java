package tetris;

import org.jetbrains.annotations.NotNull;
import tetris.common.Block;
import tetris.common.BlockColor;
import tetris.figures.Figure;

public class BlocksMatrix {
    private final int width;
    private final int height;
    private final BlockColor[][] colorsMatrix;

    public BlocksMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        colorsMatrix = new BlockColor[height][width];
        clear();
    }

    public BlocksMatrix deepCopy() {
        BlocksMatrix blocksMatrix = new BlocksMatrix(width, height);
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                blocksMatrix.set(x, y, getColor(x, y));
            }
        }
        return blocksMatrix;
    }

    public boolean isBLockInside(@NotNull Block block) {
        if (block.getY() < 0 || block.getY() >= getHeight()) return false;
        if (block.getX() < 0 || block.getX() >= getWidth()) return false;
        return true;
    }

    public void appendFigure(@NotNull Figure figure) {
        for (final Block block : figure.getBlocks()) {
            if (isBLockInside(block)) {
                set(block);
            }
        }
    }

    /**
     * removes rows from yMin to yMax
     * @param yMin -- index of first row
     * @param yMax -- inder of last row
     * @throws IndexOutOfBoundsException if indices incorrect
     */
    public void removeRows(int yMin, int yMax) throws IndexOutOfBoundsException {
        if (yMin < 0 || yMax >= height || yMin > yMax) throw new IndexOutOfBoundsException();

        int shiftValue = (yMax - yMin + 1);
        int top = height - shiftValue;

        for (int y = yMin; y < top; ++y) {
            for (int x = 0; x < width; ++x) {
                copyValue(x, y + shiftValue, x, y);
            }
        }

        for (int y = top; y < height; ++y) {
            for (int i = 0; i < width; ++i) set(i, y, null);
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
                colorsMatrix[i][j] = null;
            }
        }
    }

    private void copyValue(int xSrc, int ySrc, int xDst, int yDst) {
        BlockColor color = getColor(xSrc, ySrc);
        set(xDst, yDst, color);
    }

    private void set(int x, int y, BlockColor color) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        colorsMatrix[y][x] = color;
    }

    private void set(@NotNull Block block) throws IndexOutOfBoundsException {
        set(block.getX(), block.getY(), block.getColor());
    }

    public boolean get(int x, int y) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        return colorsMatrix[y][x] != null;
    }
    public BlockColor getColor(int x, int y) throws IndexOutOfBoundsException {
        if (x > width || x < 0 || y > height || y < 0) throw new IndexOutOfBoundsException();
        return colorsMatrix[y][x];
    }

    public boolean get(@NotNull Block block) {
        return get(block.getX(), block.getY());
    }

    public BlockColor[][] getColorsMatrix() { return colorsMatrix; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
