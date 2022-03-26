package view;

import common.ISubscriber;
import org.jetbrains.annotations.NotNull;
import tetris.common.BlocksMatrix;
import tetris.figures.BaseFigure;
import tetris.figures.Figure;

import javax.swing.*;
import java.awt.*;

public class BlocksPanel extends JPanel implements ISubscriber {
    private final int blocksX;
    private final int blocksY;
    private final int blockSize;
    private final BlockView[][] blocks;

    private BlocksMatrix blocksMatrixModel;
    private Figure figureModel;

    private final Color emptyColor = new Color(50, 50, 50);
    private final Color filledColor = new Color(100, 50, 100);

    BlocksPanel(int blocksX, int blocksY, int blockSize) {
        super(new GridLayout(blocksY, blocksX));
        this.blocksX = blocksX;
        this.blocksY = blocksY;
        this.blockSize = blockSize;

        blocks = new BlockView[blocksX][blocksY];
        for(int j = 0; j < blocksY; ++j) {
            for (int i = 0; i < blocksX; ++i) {
                blocks[i][j] = new BlockView(blockSize, emptyColor);
                this.add(blocks[i][j]);
            }
        }
    }

    public void setBlocksMatrixModel(@NotNull BlocksMatrix blocksMatrixModel) {
        if (this.blocksMatrixModel != null) {
            this.blocksMatrixModel.removeSubscriber(this);
        }
        this.blocksMatrixModel = blocksMatrixModel;
        blocksMatrixModel.addSubscriber(this);
    }

    public void setFigureModel(@NotNull BaseFigure figureModel) {
        if (this.figureModel != null) {
            this.figureModel.removeSubscriber(this);
        }
        this.figureModel = figureModel;
        figureModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        for (int y = 0; y < blocksY; ++y) {
            for (int x = 0; x < blocksX; ++x) {
                boolean hasBlock = blocksMatrixModel.get(x, y);
                if (hasBlock) {
                    blocks[x][y].setColor(filledColor);
                } else {
                    blocks[x][y].setColor(emptyColor);
                }
            }
        }
    }
}
