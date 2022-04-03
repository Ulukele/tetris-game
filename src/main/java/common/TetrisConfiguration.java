package common;

import Exceptions.LoadConfigurationException;
import tetris.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TetrisConfiguration {
    private PropertiesParser propertiesParser;

    private String filename;

    private int blocksXCount;
    private int blocksYCount;
    private int blockSize;

    private GameSpace gameSpace;
    private Score score;
    private TetrisEngine tetrisEngine;
    private ActiveFigure activeFigure;
    private GameState gameState;

    private Font font = new Font("Roboto", Font.BOLD, 25);
    private final Color backgroundColor = new Color(50, 50, 60);
    private final Color contrastColor = new Color(200, 200, 220);

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
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "PressStart2P-Regular.ttf"
            );
            if (stream == null) throw new IOException();
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(15f);
        } catch (IOException | FontFormatException exception) {
            throw new LoadConfigurationException("Can't Load font");
        }
    }

    private void configureFromDefaults() {
        blocksXCount = 10;
        blocksYCount = 20;
        blockSize = 40;
    }

    private void configureFromProperties() throws LoadConfigurationException {
        try {
            blocksXCount = propertiesParser.getInteger("BLOCKS_X_COUNT");
            blocksYCount = propertiesParser.getInteger("BLOCKS_Y_COUNT");
            blockSize = propertiesParser.getInteger("BLOCK_SIZE");
        } catch (NullPointerException | NumberFormatException exception) {
            configureFromDefaults();
            throw new LoadConfigurationException("Unable to parse configuration file, use defaults");
        }
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

    public ActiveFigure getActiveFigure() {
        if (activeFigure == null) {
            activeFigure = getGameSpace().getActiveFigure();
        }
        return activeFigure;
    }

    public Font getFont() {
        return font;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getContrastColor() {
        return contrastColor;
    }
}
