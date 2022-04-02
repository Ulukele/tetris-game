package view;

import common.ISubscriber;
import common.Model;
import common.TetrisConfiguration;
import controller.IClient;
import controller.TetrisClient;
import org.jetbrains.annotations.NotNull;
import tetris.GameStates;

import javax.swing.*;
import java.awt.*;

public class MainTetrisView extends JFrame {
    private final TetrisConfiguration configuration;

    private final ScoreLabel scoreLabel;
    private final StateLabel stateLabel;
    private final NextBlockPanel nextBlockPanel;
    private final BlocksPanel blocksMatrix;

    public MainTetrisView(@NotNull TetrisConfiguration configuration) {
        super("Tetris");

        // Base configuration of main frame
        this.configuration = configuration;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setBackground(new Color(50, 50, 50));
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Add score info
        scoreLabel = new ScoreLabel(configuration.getFont());
        scoreLabel.setMinimumSize(new Dimension(300, 20));

        stateLabel = new StateLabel(configuration.getFont());
        stateLabel.setMinimumSize(new Dimension(0, 70));

        // Add next block panel
        int nextFigureBlocksSize = configuration.getBlockSize() / 2;
        nextBlockPanel = new NextBlockPanel(nextFigureBlocksSize);
        Dimension nextBlockPanelSize = new Dimension(nextFigureBlocksSize * 4, nextFigureBlocksSize * 4);
        nextBlockPanel.setMaximumSize(nextBlockPanelSize);
        nextBlockPanel.setMinimumSize(nextBlockPanelSize);

        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix = new BlocksPanel(blocksXCount, blocksYCount, blockSize);
        blocksMatrix.setMinimumSize(new Dimension(blocksXCount * blockSize, blocksYCount * blockSize));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scoreLabel)
                                .addComponent(nextBlockPanel)
                        )
                        .addComponent(stateLabel)
                        .addComponent(blocksMatrix)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(scoreLabel)
                                .addComponent(nextBlockPanel)
                        )
                        .addComponent(stateLabel)
                        .addComponent(blocksMatrix)
        );

        // Connect models
        connectMenu();
        connectModels();

        // Finish layout configuration
        pack();
    }

    private void connectMenu() {
        IClient client = new TetrisClient(configuration.getTetrisGame());
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu(client));
        setJMenuBar(menuBar);
    }


    private void connectModels() {
        blocksMatrix.setBlocksMatrixModel(configuration.getGameSpace());
        scoreLabel.setScoreModel(configuration.getScore());
        nextBlockPanel.setNextFigureModel(configuration.getActiveFigure());
        stateLabel.setGameStatesModel(configuration.getGameState());
    }
}
