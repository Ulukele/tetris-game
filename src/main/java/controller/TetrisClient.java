package controller;

import tetris.TetrisEngine;

public class TetrisClient implements IClient {

    private final TetrisEngine tetrisEngine;

    public TetrisClient(TetrisEngine tetrisEngine) {
        this.tetrisEngine = tetrisEngine;
    }

    @Override
    public void execute(ISafeCommand command) {
        command.executeSafe(tetrisEngine);
    }

    @Override
    public void execute(ISafeCommand command, String argument) {
        command.executeSafe(tetrisEngine, argument);
    }

    @Override
    public void executeUnsafe(IUnsafeCommand command) throws Exception {
        command.executeUnsafe(tetrisEngine);
    }

    @Override
    public void executeUnsafe(IUnsafeCommand command, String argument) throws Exception {
        command.executeUnsafe(tetrisEngine, argument);
    }
}
