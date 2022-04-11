package controller;

import exceptions.HighScoresException;
import tetris.TetrisEngine;

public enum HighScoreCommand implements IUnsafeCommand{
    LoadHighScores {
        @Override
        public void executeUnsafe(TetrisEngine game) throws HighScoresException {
            game.loadHighScores();
        }
    },
    SaveScore{
        @Override
        public void executeUnsafe(TetrisEngine game, String argument) throws HighScoresException {
            game.saveResult(argument);
        }

        @Override
        public void executeUnsafe(TetrisEngine game) throws HighScoresException {
            game.saveResult("???");
        }
    };
    public abstract void executeUnsafe(TetrisEngine game) throws HighScoresException;
    public void executeUnsafe(TetrisEngine game, String argument) throws HighScoresException {
        executeUnsafe(game);
    }
}
