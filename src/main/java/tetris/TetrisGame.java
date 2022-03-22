package tetris;

import tetris.figures.Figure;

public class TetrisGame {
    private final GameSpace gameSpace;
    private final Score score = new Score(0);
    private Figure activeFigure = null;

    public TetrisGame(GameSpace gameSpace) {
        this.gameSpace = gameSpace;
    }

    public void startNewGame() {
        gameSpace.clear();
        score.clear();
    }
}
