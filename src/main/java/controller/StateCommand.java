package controller;

import tetris.TetrisEngine;

public enum StateCommand implements ISafeCommand {

    Stop {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.pause();
        }
    },
    NewGame {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.startNewGame();
        }
    },
    Continue {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.unpause();
        }
    },
    SwitchStopContinue {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.switchState();
        }
    },
    About {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.setAboutInfoState();
        }
    },
    HighScores {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.setHighScoresState();
        }
    };

    @Override
    public abstract void executeSafe(TetrisEngine game);

    @Override
    public void executeSafe(TetrisEngine game, String argument) {
        executeSafe(game);
    }
}
