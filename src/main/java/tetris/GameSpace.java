package tetris;

import common.Model;
import common.Publisher;
import org.jetbrains.annotations.NotNull;
import tetris.common.Block;
import tetris.figures.Figure;

import java.sql.Array;

/**
 * Class that contains all necessary game instances
 * responsible for the consistency of the gameplay.
 * Provides guarantees of compatibility of all game entities.
 */
public class GameSpace extends Publisher implements Model<boolean[][]> {
    private final BlocksMatrix blocksMatrix;
    private final ActiveFigure activeFigure;

    public GameSpace(int width, int height) {
        blocksMatrix = new BlocksMatrix(width, height);
        activeFigure = new ActiveFigure();
        setActiveFigureStartPosition();
        publishNotify();
    }

    private void shiftActiveFigure(int x, int y) {
        activeFigure.getActiveFigure().shift(x, y);
        publishNotify();
    }

    private void rotateActiveFigure() {
        activeFigure.getActiveFigure().rotate();
        publishNotify();
    }

    private boolean checkBlockInsideMatrix(@NotNull Block block) {
        if (block.getY() < 0 || block.getY() >= blocksMatrix.getHeight()) return false;
        if (block.getX() < 0 || block.getX() >= blocksMatrix.getWidth()) return false;
        return true;
    }

    private boolean checkBlockAndBlocksMatrixConflict(@NotNull Block block) {
        if (block.getY() < 0) return true;
        if (block.getY() >= blocksMatrix.getHeight()) return false;
        if (block.getX() >= blocksMatrix.getWidth() || block.getX() < 0) return true;
        return blocksMatrix.get(block);
    }

    private boolean checkFigureAndBlocksMatrixConflict() {
        Figure figure = activeFigure.getActiveFigure();
        for (final Block block : figure.getBlocks()) {
            if (checkBlockAndBlocksMatrixConflict(block)) return true;
        }
        return false;
    }

    private boolean activeFigureFallen() {
        activeFigure.getActiveFigure().shift(0, -1);
        boolean fallen = checkFigureAndBlocksMatrixConflict();
        activeFigure.getActiveFigure().shift(0, 1);
        return fallen;
    }

    private void setActiveFigureStartPosition() {
        activeFigure.getActiveFigure().setPos(
                blocksMatrix.getWidth() / 2,
                blocksMatrix.getHeight() - 1
        );
    }

    private void spawnNewActiveFigure() {
        for (final Block block : activeFigure.getActiveFigure().getBlocks()) {
            if (checkBlockInsideMatrix(block)) {
                blocksMatrix.set(block.getX(), block.getY(), true);
            }
        }
        activeFigure.changeActiveFigure();
        setActiveFigureStartPosition();
        publishNotify();
    }

    public void askShiftFigure(int x, int y) {
        activeFigure.getActiveFigure().shift(x, y);
        if (checkFigureAndBlocksMatrixConflict()) {
            activeFigure.getActiveFigure().shift(-x, -y);
        } else {
            publishNotify();
        }
    }

    public void askRotateFigure() {
        activeFigure.getActiveFigure().rotate(true);
        if (checkFigureAndBlocksMatrixConflict()) {
            activeFigure.getActiveFigure().rotate(false);
        } else {
            publishNotify();
        }
    }

    public int deleteFilledRows() {
        int filledRows = 0;
        for (int y = 0; y < blocksMatrix.getHeight(); ++y) {
            if (blocksMatrix.rowIsFull(y)) {
                filledRows++;
            }
        }
        blocksMatrix.shiftDown(filledRows);
        if (filledRows > 0) publishNotify();
        return filledRows;
    }

    public void fallActiveFigure() {
        if (activeFigureFallen()) {
            spawnNewActiveFigure();
        } else {
            shiftActiveFigure(0, -1);
        }
    }

    public void clear() {
        setActiveFigureStartPosition();
        blocksMatrix.clear();
    }

    public boolean[][] getBlocks() {
        boolean[][] instance = blocksMatrix.getBitMatrix();
        boolean[][] copy = new boolean[blocksMatrix.getHeight()][blocksMatrix.getWidth()];
        for (int i = 0; i < blocksMatrix.getHeight(); ++i) {
            for (int j = 0; j < blocksMatrix.getWidth(); ++j) {
                copy[i][j] = instance[i][j];
            }
        }
        for (final Block block : activeFigure.getActiveFigure().getBlocks()) {
            if (checkBlockInsideMatrix(block)) {
                copy[block.getY()][block.getX()] = true;
            }
        }
        return copy;
    }

    public int getWidth() {
        return blocksMatrix.getWidth();
    }

    public int getHeight() {
        return blocksMatrix.getHeight();
    }

    @Override
    public boolean[][] getData() {
        return getBlocks();
    }
}
