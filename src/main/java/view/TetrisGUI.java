package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TetrisGUI extends JFrame {
    private GUIConfiguration configuration;

    private final JLabel scoreLabel;
    private final JPanel blocksMatrix;

    public TetrisGUI(GUIConfiguration configuration) {
        super("Tetris");

        // Base configuration of main frame
        this.configuration = configuration;
        this.setBounds(100, 100, configuration.getWidth(), configuration.getHeight());
        this.setResizable(false);
        this.setBackground(new Color(70, 70, 70));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configure container
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Add score info
        scoreLabel = new ScoreLabel();
        container.add(scoreLabel);

        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix = new BlocksUI(blocksXCount, blocksYCount, blockSize);
        container.add(blocksMatrix);

    }
}
