import Exceptions.LoadConfigurationException;
import controller.TetrisClient;
import tetris.TetrisEngine;
import common.TetrisConfiguration;
import view.MainTetrisView;
import view.TetrisKeyListener;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        TetrisConfiguration tetrisConfiguration = new TetrisConfiguration("configuration.properties");
        try {
            tetrisConfiguration.configure();
        } catch (LoadConfigurationException configurationException) {
            configurationException.printStackTrace();
        }

        // Get game instance
        TetrisEngine tetrisEngine = tetrisConfiguration.getTetrisGame();

        // Start GUI
        MainTetrisView app = new MainTetrisView(tetrisConfiguration);

        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            app.setVisible(true);
        });

        // Start client
        TetrisClient tetrisClient = new TetrisClient(tetrisEngine);
        TetrisKeyListener tetrisKeyListener = new TetrisKeyListener(tetrisClient);
        app.addKeyListener(tetrisKeyListener);
    }
}
