package controller;

import tetris.TetrisEngine;

public enum MovementCommand implements ISafeCommand {

    Left {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.askShiftActiveFigure(-1, 0);
        }
    },
    Right {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.askShiftActiveFigure(1, 0);
        }
    },
    Down {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.askShiftActiveFigure(0, -1);
        }
    },
    Rotate {
        @Override
        public void executeSafe(TetrisEngine game) {
            game.askRotateActiveFigure();
        }
    };

    @Override
    public abstract void executeSafe(TetrisEngine game);

    @Override
    public void executeSafe(TetrisEngine game, String argument) {
        executeSafe(game);
    }
}
