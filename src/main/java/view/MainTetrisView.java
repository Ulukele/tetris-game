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

public class MainTetrisView extends JFrame implements ISubscriber {
    private final TetrisConfiguration configuration;

    private final ScoreLabel scoreLabel;
    private final BlocksPanel blocksMatrix;
    private Model<GameStates> gameStateModel;

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

        connectMenu();

        connectModels();
    }

    private void connectMenu() {
        IClient client = new TetrisClient(configuration.getTetrisGame());
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu(client));
        setJMenuBar(menuBar);
    }

    private void connectGameStateModel(Model<GameStates> gameStatesModel) {
        this.gameStateModel = gameStatesModel;
        gameStatesModel.addSubscriber(this);
    }

    private void connectModels() {
        connectGameStateModel(configuration.getGameState());
        blocksMatrix.setBlocksMatrixModel(configuration.getGameSpace());
        scoreLabel.setScoreModel(configuration.getScore());
    }

    @Override
    public void reactOnNotify() {
        GameStates state = gameStateModel.getData();
        if (state == GameStates.Pause) {
            // show 'Start new game' 'Resume' 'Exit'
        } else if (state == GameStates.Lose) {
            // show 'Start new game' 'Exit'
        } else {
            // close such objects as Menu and LoseMenu
        }
    }
}
