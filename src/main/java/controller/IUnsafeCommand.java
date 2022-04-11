package controller;

import tetris.TetrisEngine;

public interface IUnsafeCommand {
    void executeUnsafe(TetrisEngine game) throws Exception;
    void executeUnsafe(TetrisEngine game, String argument) throws Exception;
}
