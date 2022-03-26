package tetris;

import org.jetbrains.annotations.NotNull;
import tetris.common.Block;
import tetris.common.BlocksMatrix;
import tetris.figures.Figure;

public class GameSpace {
    private final BlocksMatrix blocksMatrix;
    private Figure activeFigure; // TODO: move active figure to that class, work with figure here

    public GameSpace(int width, int height) {
        blocksMatrix = new BlocksMatrix(width, height);
    }

    /**
     * Check if some block conflicts with
     * current block's matrix (overlay it)
     * @param block -- block to check on conflict
     * @return true if block conflicting with current matrix
     */
    private boolean checkBlockConflict(@NotNull Block block) {
        if (block.getX() < 0 || block.getX() >= getWidth()) return true;
        if (block.getY() < 0 || block.getY() >= getHeight()) return true;
        return blocksMatrix.get(block);
    }

    /**
     * Check if bottom rows is full, delete them and shift down
     */
    private void deleteFullRows() {
        int height = blocksMatrix.getHeight();
        int fullRows = 0;
        for (int i = 0; i < height; ++i) {
            if (blocksMatrix.rowIsFull(i)) fullRows++;
            else break;
        }
        if (fullRows > 0) {
            blocksMatrix.shiftDown(fullRows);
        }
    }

    public void updateRows() {
        deleteFullRows();
    }

    public void clear() {
        blocksMatrix.clear();
    }

    public void appendFigure(@NotNull Figure figure) {
        Block[] blocks = figure.getBlocks();
        for (final Block block : blocks) {
            blocksMatrix.set(block, true);
        }
    }

    public boolean checkFigureConflict(@NotNull Figure figure) {
        Block[] blocks = figure.getBlocks();
        for (final Block block : blocks) {
            if (checkBlockConflict(block)) return true;
        }
        return false;
    }

    public int getWidth() { return blocksMatrix.getWidth(); }
    public int getHeight() { return blocksMatrix.getHeight(); }
    public BlocksMatrix getBlocksMatrix() {
        return blocksMatrix;
    }
}
