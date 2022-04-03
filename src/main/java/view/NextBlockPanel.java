package view;

import common.ISubscriber;
import common.Model;
import org.jetbrains.annotations.NotNull;
import tetris.common.Block;

import javax.swing.*;
import java.awt.*;

public class NextBlockPanel extends JPanel implements ISubscriber {
    private Model<Block[]> nextFigureModel;
    private final int blocksX = 4;
    private final int blocksY = 4;
    private final BlockView[][] blocksMatrix;

    private final Color emptyColor;
    private final Color filledColor;

    public NextBlockPanel(int blockSize, Color backgroundColor, Color figureColor) {
        super();
        this.emptyColor = backgroundColor;
        this.filledColor = figureColor;
        setLayout(new GridLayout(blocksX, blocksY));

        blocksMatrix = new BlockView[blocksY][blocksX];
        for(int j = blocksY - 1; j >= 0; --j) {
            for (int i = 0; i < blocksX; ++i) {
                blocksMatrix[j][i] = new BlockView(blockSize, emptyColor);
                this.add(blocksMatrix[j][i]);
            }
        }
    }

    public void setNextFigureModel(@NotNull Model<Block[]> nextFigureModel) {
        this.nextFigureModel = nextFigureModel;
        nextFigureModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        for (int x = 0; x < blocksX; ++x) {
            for (int y = 0; y < blocksY; ++y) {
                blocksMatrix[y][x].setColor(emptyColor);
            }
        }

        Block[] blocks = nextFigureModel.getData();

        int shiftX = Integer.MAX_VALUE;
        int shiftY = Integer.MAX_VALUE;
        for (final Block block : blocks) {
            shiftX = Math.min(shiftX, block.getX());
            shiftY = Math.min(shiftY, block.getY());
        }

        for (final Block block : blocks) {
            int x = block.getX() - shiftX;
            int y = block.getY() - shiftY;
            blocksMatrix[y][x].setColor(filledColor);
        }

        repaint();
    }
}
