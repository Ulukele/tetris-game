package view;

import common.ISubscriber;
import common.Model;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ScoreLabel extends JLabel implements ISubscriber {

    private Model<Integer> scoreModel;

    ScoreLabel() {
        super("SCORE: ");
    }

    public void setScoreModel(@NotNull Model<Integer> scoreModel) {
        if (this.scoreModel != null) {
            this.scoreModel.removeSubscriber(this);
        }
        this.scoreModel = scoreModel;
        scoreModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        this.setText("SCORE: " + scoreModel.getData());
    }
}
