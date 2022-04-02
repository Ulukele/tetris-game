package view;

import common.ISubscriber;
import common.Model;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel implements ISubscriber {

    private Model<Integer> scoreModel;

    ScoreLabel(Font font) {
        super("SCORE: ");
        setFont(font);
        setForeground(new Color(200, 200, 200));
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
