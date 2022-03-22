package tetris;

import org.jetbrains.annotations.NotNull;
import tetris.common.Point;
import tetris.common.PointsMatrix;
import tetris.figures.Figure;

public class GameSpace {
    private final PointsMatrix pointsMatrix;

    public GameSpace(int width, int height) {
        pointsMatrix = new PointsMatrix(width, height);
    }

    /**
     * Check if some point conflicts with
     * current point's matrix (overlay it)
     * @param point -- point to check on conflict
     * @return true if point conflicting with current matrix
     */
    private boolean checkPointConflict(@NotNull Point point) {
        if (point.getX() < 0 || point.getX() >= getWidth()) return true;
        if (point.getY() < 0 || point.getY() >= getHeight()) return true;
        return pointsMatrix.get(point);
    }

    /**
     * Check if bottom rows is full, delete them and shift down
     */
    private void deleteFullRows() {
        int height = pointsMatrix.getHeight();
        int fullRows = 0;
        for (int i = 0; i < height; ++i) {
            if (pointsMatrix.rowIsFull(i)) fullRows++;
            else break;
        }
        if (fullRows > 0) {
            pointsMatrix.shiftDown(fullRows);
        }
    }

    public void updateRows() {
        deleteFullRows();
    }

    public void clear() {
        pointsMatrix.clear();
    }

    public void appendFigure(@NotNull Figure figure) {
        Point[] blocks = figure.getBlocks();
        for (final Point block : blocks) {
            pointsMatrix.set(block, true);
        }
    }

    public boolean checkFigureConflict(@NotNull Figure figure) {
        Point[] blocks = figure.getBlocks();
        for (final Point block : blocks) {
            if (checkPointConflict(block)) return true;
        }
        return false;
    }

    public int getWidth() { return pointsMatrix.getWidth(); }
    public int getHeight() { return pointsMatrix.getHeight(); }
}
