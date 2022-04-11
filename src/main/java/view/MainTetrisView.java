package view;

import common.ISubscriber;
import common.Model;
import common.TetrisConfiguration;
import controller.HighScoreCommand;
import controller.IClient;
import org.jetbrains.annotations.NotNull;
import tetris.GameStates;
import tetris.ModelsManager;

import javax.swing.*;
import java.awt.*;

public class MainTetrisView extends JFrame implements ISubscriber {
    private final TetrisConfiguration configuration;
    private IClient client;

    private final AboutInfoPanel aboutInfoPanel;
    private final ScoreLabel scoreLabel;
    private final StateLabel stateLabel;
    private final NextBlockPanel nextBlockPanel;
    private final BlocksPanel blocksMatrix;
    private final HighScoresTablePanel highScoresTablePanel;

    private Model<GameStates> gameStatesModel;

    public MainTetrisView(@NotNull TetrisConfiguration configuration) {
        super("Tetris");

        // Base configuration of main frame
        this.configuration = configuration;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        highScoresTablePanel = new HighScoresTablePanel(configuration.getUserScoresTableRowsCount());
        aboutInfoPanel = new AboutInfoPanel();
        scoreLabel = new ScoreLabel();
        stateLabel = new StateLabel();


        // Add next block panel
        int nextFigureBlocksSize = configuration.getBlockSize() / 2;
        nextBlockPanel = new NextBlockPanel(
                nextFigureBlocksSize,
                configuration.getBackgroundColor(),
                configuration.getContrastColor()
        );
        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix = new BlocksPanel(blocksXCount, blocksYCount, blockSize, configuration.getBackgroundColor());
    }

    public void configureLayout() {
        Container container = getContentPane();
        container.setBackground(configuration.getBackgroundColor());
        GroupLayout layout = new GroupLayout(container);
        container.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Add scores table
        highScoresTablePanel.setBackground(configuration.getBackgroundColor().brighter());
        highScoresTablePanel.getLabel().setFont(configuration.getFont());
        highScoresTablePanel.getLabel().setForeground(configuration.getContrastColor());
        highScoresTablePanel.getTable().setBackground(configuration.getContrastColor());
        highScoresTablePanel.getTable().setFont(configuration.getFont().deriveFont(10f));
        highScoresTablePanel.setVisible(false);

        // Add about info
        aboutInfoPanel.getInfoLabel().setFont(configuration.getFont());
        aboutInfoPanel.getInfoLabel().setForeground(configuration.getContrastColor());
        aboutInfoPanel.setBackground(configuration.getBackgroundColor().brighter());

        // Add score info
        scoreLabel.setFont(configuration.getFont());
        scoreLabel.setForeground(configuration.getContrastColor());
        scoreLabel.setMinimumSize(new Dimension(300, 20));

        stateLabel.setFont(configuration.getFont());
        stateLabel.setForeground(configuration.getContrastColor());
        stateLabel.setMinimumSize(new Dimension(0, 70));

        // Add next block panel
        int nextFigureBlocksSize = configuration.getBlockSize() / 2;

        Dimension nextBlockPanelSize = new Dimension(nextFigureBlocksSize * 4, nextFigureBlocksSize * 4);
        nextBlockPanel.setMaximumSize(nextBlockPanelSize);
        nextBlockPanel.setMinimumSize(nextBlockPanelSize);

        // Add matrix with blocks
        int blockSize = configuration.getBlockSize();
        int blocksXCount = configuration.getBlocksXCount();
        int blocksYCount = configuration.getBlocksYCount();
        blocksMatrix.setMinimumSize(new Dimension(blocksXCount * blockSize, blocksYCount * blockSize));

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scoreLabel)
                                .addComponent(nextBlockPanel)
                        )
                        .addComponent(stateLabel)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(highScoresTablePanel)
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
                                .addComponent(highScoresTablePanel)
                                .addComponent(aboutInfoPanel)
                                .addComponent(blocksMatrix)
                        )
        );

        pack();
    }

    private void tryToLoadHighScores() {
        try {
            client.executeUnsafe(HighScoreCommand.LoadHighScores);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Failed to load high-scores");
        }
    }

    public void connectControl(IClient tetrisClient) {
        client = tetrisClient;
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new GameMenu(tetrisClient));
        setJMenuBar(menuBar);

        TetrisKeyListener tetrisKeyListener = new TetrisKeyListener(tetrisClient);
        addKeyListener(tetrisKeyListener);
    }

    private void connectModel(Model<GameStates> gameStatesModel) {
        this.gameStatesModel = gameStatesModel;
        gameStatesModel.addSubscriber(this);
    }

    public void connectModels(ModelsManager modelsManager) {
        blocksMatrix.setBlocksMatrixModel(modelsManager.getGameSpace());
        scoreLabel.setScoreModel(modelsManager.getScoreCounter());
        nextBlockPanel.setNextFigureModel(modelsManager.getActiveFigure());
        stateLabel.setGameStatesModel(modelsManager.getGameState());
        highScoresTablePanel.setUserScoresListModel(modelsManager.getUserScoresTable());
        connectModel(modelsManager.getGameState());
        tryToLoadHighScores();
    }

    @Override
    public void reactOnNotify() {
        GameStates gameState = gameStatesModel.getData();

        aboutInfoPanel.setVisible(false);
        highScoresTablePanel.setVisible(false);

        if (gameState == GameStates.AboutInfo) {
            aboutInfoPanel.setVisible(true);
        } else if (gameState == GameStates.HighScores) {
            highScoresTablePanel.setVisible(true);
        } else if (gameState == GameStates.Lose) {
            registerResult();
        }
    }

    private void registerResult() {
        if (client == null) return;

        String userName = JOptionPane.showInputDialog("Enter your name:");

        try {
            client.executeUnsafe(HighScoreCommand.SaveScore, userName);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Can't save result");
        }
    }
}
