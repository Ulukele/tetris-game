package tetris;

public class Score {
    private int score;

    public Score() { score = 0; }
    public Score(int score) { this.score = score; }

    public int getScore() {
        return score;
    }

    public void increase(int value) {
        score += value;
    }
    public void clear() { score = 0; }
}
