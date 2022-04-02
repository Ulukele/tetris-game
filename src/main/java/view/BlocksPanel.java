package view;

import common.ISubscriber;
import common.Model;
import tetris.common.BlockColor;

import javax.swing.*;
import java.awt.*;

public class BlocksPanel extends JPanel implements ISubscriber {
    private final int blocksX;
    private final int blocksY;
    private final BlockView[][] blocks;

    private Model<BlockColor[][]> blocksMatrixModel;

    private final Color emptyColor = new Color(50, 50, 50);
    private final Color redColor = new Color(150, 70, 70);
    private final Color orangeColor = new Color(199, 114, 46);
    private final Color yellowColor = new Color(212, 187, 75);
    private final Color greenColor = new Color(78, 171, 69);
    private final Color blueColor = new Color(60, 136, 178);
    private final Color purpleColor = new Color(147, 57, 200);


    BlocksPanel(int blocksX, int blocksY, int blockSize) {
        super(new GridLayout(blocksY, blocksX));
        this.blocksX = blocksX;
        this.blocksY = blocksY;

        blocks = new BlockView[blocksY][blocksX];
        for(int j = blocksY - 1; j >= 0; --j) {
            for (int i = 0; i < blocksX; ++i) {
                blocks[j][i] = new BlockView(blockSize, emptyColor);
                this.add(blocks[j][i]);
            }
        }
    }

    public void setBlocksMatrixModel(Model<BlockColor[][]> blocksMatrixModel) {
        this.blocksMatrixModel = blocksMatrixModel;
        blocksMatrixModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        BlockColor[][] blocksMatrix = blocksMatrixModel.getData();
        for (int x = 0; x < blocksX; ++x) {
            for (int y = 0; y < blocksY; ++y) {
                if (blocksMatrix[y][x] == null) {
                    blocks[y][x].setColor(emptyColor);
                } else if (blocksMatrix[y][x] == BlockColor.RED) {
                    blocks[y][x].setColor(redColor);
                } else if (blocksMatrix[y][x] == BlockColor.ORANGE) {
                    blocks[y][x].setColor(orangeColor);
                } else if (blocksMatrix[y][x] == BlockColor.YELLOW) {
                    blocks[y][x].setColor(yellowColor);
                } else if (blocksMatrix[y][x] == BlockColor.GREEN) {
                    blocks[y][x].setColor(greenColor);
                } else if (blocksMatrix[y][x] == BlockColor.BLUE) {
                    blocks[y][x].setColor(blueColor);
                } else if (blocksMatrix[y][x] == BlockColor.PURPLE) {
                    blocks[y][x].setColor(purpleColor);
                }
            }
        }
        this.repaint();
    }
}
