package view;

import common.ISubscriber;
import org.jetbrains.annotations.NotNull;
import tetris.Score;

import javax.swing.*;

public class ScoreLabel extends JLabel implements ISubscriber {

    private Score score;

    ScoreLabel() {
        super("Score: ");
    }

    public void setScoreModel(@NotNull Score score) {
        if (this.score != null) {
            this.score.removeSubscriber(this);
        }
        this.score = score;
        score.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        this.setText("Score: " + score.getScore());
    }
}
