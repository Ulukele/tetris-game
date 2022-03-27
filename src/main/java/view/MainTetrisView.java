package view;

import common.TetrisConfiguration;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MainTetrisView extends JFrame {
    private TetrisConfiguration configuration;

    private final ScoreLabel scoreLabel;
    private final BlocksPanel blocksMatrix;

    public MainTetrisView(@NotNull TetrisConfiguration configuration) {
        super("Tetris");

        // Base configuration of main frame
        this.configuration = configuration;
        this.setBounds(500, 100, configuration.getWidth(), configuration.getHeight());
        this.setResizable(false);
        this.setBackground(new Color(50, 50, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configure container
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Add score info
        scoreLabel = new ScoreLabel();
        scoreLabel.setBounds(
                configuration.getPadding(),
                configuration.getPadding(),
                configuration.getWidth(),
                configuration.getTopSize()
        );
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(scoreLabel);

        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix = new BlocksPanel(blocksXCount, blocksYCount, blockSize);
        blocksMatrix.setBounds(
                configuration.getPadding(),
                configuration.getPadding() + configuration.getTopSize(),
                blocksXCount * blockSize,
                blocksYCount * blockSize
        );
        blocksMatrix.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(blocksMatrix);

        connectModels();
    }

    private void connectModels() {
        blocksMatrix.setBlocksMatrixModel(configuration.getGameSpace());
    }
}
