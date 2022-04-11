import exceptions.LoadConfigurationException;
import controller.TetrisClient;
import tetris.ModelsManager;
import tetris.TetrisEngine;
import common.TetrisConfiguration;
import view.MainTetrisView;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        TetrisConfiguration tetrisConfiguration = new TetrisConfiguration("configuration.properties");
        try {
            tetrisConfiguration.configure();
        } catch (LoadConfigurationException configurationException) {
            configurationException.printStackTrace();
        }

        ModelsManager modelsManager = new ModelsManager(tetrisConfiguration);

        // Get game instance
        TetrisEngine tetrisEngine = modelsManager.getTetrisEngine();
        TetrisClient tetrisClient = new TetrisClient(tetrisEngine);

        // Start GUI
        MainTetrisView app = new MainTetrisView(tetrisConfiguration);

        SwingUtilities.invokeLater(() -> {
            app.connectControl(tetrisClient);
            app.connectModels(modelsManager);
            app.configureLayout();
            app.setVisible(true);
        });
    }
}
