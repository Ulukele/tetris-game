package controller;

import tetris.TetrisEngine;

public interface ISafeCommand {
    void executeSafe(TetrisEngine game);
    void executeSafe(TetrisEngine game, String argument);
}
