import Exceptions.LoadConfigurationException;
import controller.TetrisClient;
import tetris.TetrisEngine;
import common.TetrisConfiguration;
import view.MainTetrisView;
import view.TetrisKeyListener;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {

        //  TODO: understand how to find resources properly
        TetrisConfiguration tetrisConfiguration = new TetrisConfiguration("src/main/resources/configuration.properties");
        try {
            tetrisConfiguration.configure();
        } catch (LoadConfigurationException configurationException) {
            configurationException.printStackTrace();
        }

        // Get game instance
        TetrisEngine tetrisEngine = tetrisConfiguration.getTetrisGame();

        // Start GUI
        MainTetrisView app = new MainTetrisView(tetrisConfiguration);
        app.setVisible(true);

        // Start client
        TetrisClient tetrisClient = new TetrisClient(tetrisEngine);
        TetrisKeyListener tetrisKeyListener = new TetrisKeyListener(tetrisClient);
        app.addKeyListener(tetrisKeyListener);

        // Launch game
        tetrisEngine.startNewGame();
        
    }
}
