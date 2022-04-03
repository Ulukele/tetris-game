package view;

import common.ISubscriber;
import common.Model;
import common.TetrisConfiguration;
import controller.IClient;
import controller.TetrisClient;
import org.jetbrains.annotations.NotNull;
import tetris.GameState;
import tetris.GameStates;

import javax.swing.*;
import java.awt.*;

public class MainTetrisView extends JFrame implements ISubscriber {
    private final TetrisConfiguration configuration;

    private final AboutInfoPanel aboutInfoPanel;
    private final ScoreLabel scoreLabel;
    private final StateLabel stateLabel;
    private final NextBlockPanel nextBlockPanel;
    private final BlocksPanel blocksMatrix;

    private Model<GameStates> gameStatesModel;

    public MainTetrisView(@NotNull TetrisConfiguration configuration) {
        super("Tetris");

        // Base configuration of main frame
        this.configuration = configuration;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setBackground(configuration.getBackgroundColor());
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Add about info
        aboutInfoPanel = new AboutInfoPanel();
        aboutInfoPanel.getInfoLabel().setFont(configuration.getFont());
        aboutInfoPanel.getInfoLabel().setForeground(configuration.getContrastColor());
        aboutInfoPanel.setBackground(configuration.getBackgroundColor());

        // Add score info
        scoreLabel = new ScoreLabel();
        scoreLabel.setFont(configuration.getFont());
        scoreLabel.setForeground(configuration.getContrastColor());
        scoreLabel.setMinimumSize(new Dimension(300, 20));

        stateLabel = new StateLabel();
        stateLabel.setFont(configuration.getFont());
        stateLabel.setForeground(configuration.getContrastColor());
        stateLabel.setMinimumSize(new Dimension(0, 70));

        // Add next block panel
        int nextFigureBlocksSize = configuration.getBlockSize() / 2;
        nextBlockPanel = new NextBlockPanel(
                nextFigureBlocksSize,
                configuration.getBackgroundColor(),
                configuration.getContrastColor()
        );
        Dimension nextBlockPanelSize = new Dimension(nextFigureBlocksSize * 4, nextFigureBlocksSize * 4);
        nextBlockPanel.setMaximumSize(nextBlockPanelSize);
        nextBlockPanel.setMinimumSize(nextBlockPanelSize);

        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix = new BlocksPanel(blocksXCount, blocksYCount, blockSize, configuration.getBackgroundColor());
        blocksMatrix.setMinimumSize(new Dimension(blocksXCount * blockSize, blocksYCount * blockSize));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scoreLabel)
                                .addComponent(nextBlockPanel)
                        )
                        .addComponent(stateLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(aboutInfoPanel)
                                .addComponent(blocksMatrix)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(scoreLabel)
                                .addComponent(nextBlockPanel)
                        )
                        .addComponent(stateLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(aboutInfoPanel)
                                .addComponent(blocksMatrix)
                        )
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

    private void connectModel() {
        gameStatesModel = configuration.getGameState();
        gameStatesModel.addSubscriber(this);
    }

    private void connectModels() {
        blocksMatrix.setBlocksMatrixModel(configuration.getGameSpace());
        scoreLabel.setScoreModel(configuration.getScore());
        nextBlockPanel.setNextFigureModel(configuration.getActiveFigure());
        stateLabel.setGameStatesModel(configuration.getGameState());
        connectModel();
    }

    @Override
    public void reactOnNotify() {
        GameStates gameState = gameStatesModel.getData();
        if (gameState == GameStates.AboutInfo) {
            aboutInfoPanel.setVisible(true);
        } else {
            aboutInfoPanel.setVisible(false);
        }
    }
}
