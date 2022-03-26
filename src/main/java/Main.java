import Exceptions.LoadConfigurationException;
import tetris.GameSpace;
import tetris.Score;
import tetris.TetrisGame;
import common.TetrisConfiguration;
import tetris.common.Block;
import view.MainTetrisView;

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

        // Get game instances
        GameSpace gameSpace = tetrisConfiguration.getGameSpace();
        Score score = tetrisConfiguration.getScore();
        TetrisGame tetrisGame = new TetrisGame(gameSpace, score);


        // Start GUI
        MainTetrisView app = new MainTetrisView(tetrisConfiguration);
        app.setVisible(true);
    }
}
