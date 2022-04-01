package common;

import Exceptions.LoadConfigurationException;
import tetris.*;

import java.io.IOException;
import java.util.Properties;

public class TetrisConfiguration {
    private PropertiesParser propertiesParser;

    private String filename;

    private int width;
    private int height;
    private int blocksXCount;
    private int blocksYCount;
    private int blockSize;
    private int topSize;
    private int padding;

    private GameSpace gameSpace;
    private Score score;
    private TetrisEngine tetrisEngine;
    private GameState gameState;

    public TetrisConfiguration(String filename) {
        this.filename = filename;
    }

    public TetrisConfiguration(Properties configProperties)  {
        propertiesParser = new PropertiesParser(configProperties);
    }

    public TetrisConfiguration() {}

    public void configure() throws LoadConfigurationException {
        // Configure parameters
        if (filename != null) {
            try {
                propertiesParser = new PropertiesParser(filename);
            } catch (IOException ioException) {
                configureFromDefaults();
                throw new LoadConfigurationException(
                        "Unable to load configuration file " + '\"'+ filename +'\"' + ", use defaults"
                );
            }
        }
        if (propertiesParser != null) {
            configureFromProperties();
        } else {
            configureFromDefaults();
        }
    }

    private void calculateConfiguration() {
        width = blocksXCount * blockSize + padding * 2;
        height = blocksYCount * blockSize + padding * 2 + topSize;
    }

    private void configureFromDefaults() {
        blocksXCount = 10;
        blocksYCount = 20;
        blockSize = 40;
        topSize = 50;
        padding = 20;
        calculateConfiguration();
    }

    private void configureFromProperties() throws LoadConfigurationException{
        try {
            blocksXCount = propertiesParser.getInteger("BLOCKS_X_COUNT");
            blocksYCount = propertiesParser.getInteger("BLOCKS_Y_COUNT");
            blockSize = propertiesParser.getInteger("BLOCK_SIZE");
            topSize = propertiesParser.getInteger("TOP_SIZE");
            padding = propertiesParser.getInteger("PADDING");
        } catch (NullPointerException | NumberFormatException exception) {
            configureFromDefaults();
            throw new LoadConfigurationException("Unable to parse configuration file, use defaults");
        }
        calculateConfiguration();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBlocksXCount() {
        return blocksXCount;
    }

    public int getBlocksYCount() {
        return blocksYCount;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getTopSize() {
        return topSize;
    }

    public int getPadding() {
        return padding;
    }

    public GameSpace getGameSpace() {
        if (gameSpace == null) {
            gameSpace = new GameSpace(blocksXCount, blocksYCount);
        }
        return gameSpace;
    }

    public Score getScore() {
        if (score == null) {
            score = new Score();
        }
        return score;
    }

    public GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState(GameStates.Pause);
        }
        return gameState;
    }

    public TetrisEngine getTetrisGame() {
        if (tetrisEngine == null) {
            tetrisEngine = new TetrisEngine(getGameSpace(), getScore(), getGameState());
        }
        return tetrisEngine;
    }
}
