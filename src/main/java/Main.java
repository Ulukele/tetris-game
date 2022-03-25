import Exceptions.LoadConfigurationException;
import model.Model;
import tetris.GameSpace;
import tetris.Score;
import tetris.TetrisGame;
import tetris.common.BlocksMatrix;
import view.GUIConfiguration;
import view.TetrisGUI;

public class Main {

    public static void main(String[] args) {

        //  TODO: understand how to find resources properly
        GUIConfiguration guiConfiguration = new GUIConfiguration("src/main/resources/configuration.properties");
        try {
            guiConfiguration.configure();
        } catch (LoadConfigurationException configurationException) {
            configurationException.printStackTrace();
        }

        // Start game instance
        GameSpace gameSpace = new GameSpace(guiConfiguration.getWidth(), guiConfiguration.getHeight());
        TetrisGame tetrisGame = new TetrisGame(gameSpace);

        // Create models
        Model<Score> gameScoreModel = new Model<>(tetrisGame.getScore());
        Model<BlocksMatrix> blocksMatrixModel = new Model<BlocksMatrix>(tetrisGame.getGameSpace().getBlocksMatrix());

        // Start GUI
        TetrisGUI app = new TetrisGUI(guiConfiguration);
        app.setVisible(true);
    }
}
