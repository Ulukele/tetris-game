package tetris;

import common.Model;
import common.Publisher;
import exceptions.ActiveFigureException;
import org.jetbrains.annotations.NotNull;
import tetris.common.Block;
import tetris.common.BlockColor;
import tetris.figures.Figure;


/**
 * Class that contains all necessary game instances
 * responsible for the consistency of the gameplay.
 * Provides guarantees of compatibility of all game entities.
 */
public class GameSpace extends Publisher implements Model<BlockColor[][]> {
    private final BlocksMatrix blocksMatrix;
    private final ActiveFigure activeFigure;

    public GameSpace(int width, int height) throws ActiveFigureException {
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

    /**
     * @return true if success, false otherwise
     */
    private boolean tryToSpawnNewActiveFigure() {
        blocksMatrix.appendFigure(activeFigure.getActiveFigure());
        activeFigure.changeActiveFigure();
        setActiveFigureStartPosition();
        boolean conflicts = checkFigureAndBlocksMatrixConflict();
        publishNotify();
        return !conflicts;
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
        int filledInARow = 0;
        int height = blocksMatrix.getHeight();
        for (int y = 0; y < height; ++y) {
            if (blocksMatrix.rowIsFull(y)) {
                filledInARow++;
            } else if (filledInARow > 0) {
                filledRows += filledInARow;
                blocksMatrix.removeRows(y - filledInARow, y - 1);
                filledInARow = 0;
            }
        }
        if (filledInARow > 0) {
            filledRows += filledInARow;
            blocksMatrix.removeRows(height - filledInARow, height - 1);
        }
        if (filledRows > 0) publishNotify();
        return filledRows;
    }

    /**
     * @return true if success, false otherwise
     */
    public boolean tryToFallActiveFigure() {
        if (activeFigureFallen()) {
            return tryToSpawnNewActiveFigure();
        } else {
            shiftActiveFigure(0, -1);
        }
        return true;
    }

    public void clear() {
        activeFigure.changeActiveFigure();
        setActiveFigureStartPosition();
        blocksMatrix.clear();
    }

    public BlockColor[][] getBlocksColors() {
        BlocksMatrix copy = blocksMatrix.deepCopy();
        copy.appendFigure(activeFigure.getActiveFigure());
        return copy.getColorsMatrix();
    }

    public int getWidth() {
        return blocksMatrix.getWidth();
    }

    public int getHeight() {
        return blocksMatrix.getHeight();
    }

    public ActiveFigure getActiveFigure() {
        return activeFigure;
    }

    @Override
    public BlockColor[][] getData() {
        return getBlocksColors();
    }
}
