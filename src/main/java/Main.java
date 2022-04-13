import exceptions.LoadConfigurationException;
import controller.TetrisClient;
import exceptions.ModelsException;
import tetris.ModelsManager;
import tetris.TetrisEngine;
import common.TetrisConfiguration;
import view.MainTetrisView;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

        TetrisConfiguration tetrisConfiguration = new TetrisConfiguration("configuration.properties");
        ModelsManager modelsManager;
        try {
            tetrisConfiguration.configure();
            modelsManager = new ModelsManager(tetrisConfiguration);
        } catch (LoadConfigurationException | ModelsException exception) {
            exception.printStackTrace();
            return;
        }


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
