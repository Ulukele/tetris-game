package view;

import common.ISubscriber;
import tetris.Score;

import javax.swing.*;

public class ScoreLabel extends JLabel implements ISubscriber {

    private Score score;

    ScoreLabel() {
        super("Score: ");
    }

    @Override
    public void reactOnNotify() {
        this.setText("Score: " + score.getScore());
    }
}
