package view;

import common.ISubscriber;
import common.Model;

import javax.swing.*;
import java.awt.*;

public class BlocksPanel extends JPanel implements ISubscriber {
    private final int blocksX;
    private final int blocksY;
    private final int blockSize;
    private final BlockView[][] blocks;

    private Model<boolean[][]> blocksMatrixModel;

    private final Color emptyColor = new Color(50, 50, 50);
    private final Color filledColor = new Color(100, 50, 100);

    BlocksPanel(int blocksX, int blocksY, int blockSize) {
        super(new GridLayout(blocksY, blocksX));
        this.blocksX = blocksX;
        this.blocksY = blocksY;
        this.blockSize = blockSize;

        blocks = new BlockView[blocksY][blocksX];
        for(int j = blocksY - 1; j >= 0; --j) {
            for (int i = 0; i < blocksX; ++i) {
                blocks[j][i] = new BlockView(blockSize, emptyColor);
                this.add(blocks[j][i]);
            }
        }
    }

    public void setBlocksMatrixModel(Model<boolean[][]> blocksMatrixModel) {
        this.blocksMatrixModel = blocksMatrixModel;
        blocksMatrixModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        boolean[][] blocksMatrix = blocksMatrixModel.getData();
        for (int x = 0; x < blocksX; ++x) {
            for (int y = 0; y < blocksY; ++y) {
                if (blocksMatrix[y][x]) {
                    blocks[y][x].setColor(filledColor);
                } else {
                    blocks[y][x].setColor(emptyColor);
                }
            }
        }
        this.repaint();
    }
}
