package tetris;

import common.Model;
import common.TetrisConfiguration;
import tetris.common.Block;
import tetris.common.BlockColor;
import tetris.highScores.UserScore;
import tetris.highScores.UserScoresTable;

import java.util.List;

public class ModelsManager {
    private final GameSpace gameSpace;
    private final ScoreCounter scoreCounter;
    private final TetrisEngine tetrisEngine;
    private final ActiveFigure activeFigure;
    private final GameState gameState;
    private final UserScoresTable userScoresTable;

    public ModelsManager(TetrisConfiguration configuration) {
        gameSpace = new GameSpace(configuration.getBlocksXCount(), configuration.getBlocksYCount());
        scoreCounter = new ScoreCounter();
        gameState = new GameState(GameStates.Pause);
        activeFigure = gameSpace.getActiveFigure();
        userScoresTable = new UserScoresTable(
                configuration.getUserScoresTableRowsCount()
        );
        tetrisEngine = new TetrisEngine(gameSpace, scoreCounter, gameState, userScoresTable);
    }


    public Model<BlockColor[][]> getGameSpace() {
        return gameSpace;
    }

    public Model<Integer> getScoreCounter() {
        return scoreCounter;
    }

    public TetrisEngine getTetrisEngine() {
        return tetrisEngine;
    }

    public Model<Block[]> getActiveFigure() {
        return activeFigure;
    }

    public Model<GameStates> getGameState() {
        return gameState;
    }

    public Model<List<UserScore>> getUserScoresTable() {
        return userScoresTable;
    }
}
