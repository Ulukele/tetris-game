package tetris;

import common.Model;
import common.Publisher;

public class Score extends Publisher implements Model<Integer> {
    private int score;

    public Score() {
        score = 0;
        publishNotify();
    }
    public Score(int score) { this.score = score; }

    public int getScore() {
        return score;
    }

    public void increase(int value) {
        score += value;
        publishNotify();
    }
    public void clear() { score = 0; }

    @Override
    public Integer getData() {
        return getScore();
    }
}
