package view;

import Exceptions.LoadConfigurationException;
import common.PropertiesParser;

import java.io.IOException;
import java.util.Properties;

public class GUIConfiguration {
    private PropertiesParser propertiesParser;

    private String filename;

    private int width;
    private int height;
    private int blocksXCount;
    private int blocksYCount;
    private int blockSize;
    private int topSize;

    public GUIConfiguration(String filename) {
        this.filename = filename;
    }

    public GUIConfiguration(Properties configProperties)  {
        propertiesParser = new PropertiesParser(configProperties);
    }

    public GUIConfiguration() {}

    public void configure() throws LoadConfigurationException {
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
        int padding = 20;
        width = blocksXCount * blockSize + padding * 2;
        height = blocksYCount * blockSize + padding * 2 + topSize;
    }

    private void configureFromDefaults() {
        blocksXCount = 10;
        blocksYCount = 20;
        blockSize = 40;
        topSize = 50;
        calculateConfiguration();
    }

    private void configureFromProperties() throws LoadConfigurationException{
        try {
            blocksXCount = propertiesParser.getInteger("BLOCKS_X_COUNT");
            blocksYCount = propertiesParser.getInteger("BLOCKS_Y_COUNT");
            blockSize = propertiesParser.getInteger("BLOCK_SIZE");
            topSize = propertiesParser.getInteger("TOP_SIZE");
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
}
