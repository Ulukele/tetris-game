package tetris;

import common.Model;
import common.Publisher;

public class GameState extends Publisher implements Model<GameStates> {
    private GameStates state;

    public GameState(GameStates state) {
        this.state = state;
    }

    public void setState(GameStates state) {
        this.state = state;
        publishNotify();
    }

    @Override
    public GameStates getData() {
        return state;
    }
}
