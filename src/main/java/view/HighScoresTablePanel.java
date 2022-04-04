package view;

import common.ISubscriber;
import common.Model;
import tetris.highScores.UserScore;

import javax.swing.*;
import java.util.List;

public class HighScoresTablePanel extends JPanel implements ISubscriber {
    private Model<List<UserScore>> userScoresListModel;
    private List<UserScore> userScores;

    public HighScoresTablePanel() {
        super();

    }

    @Override
    public void reactOnNotify() {
        userScores = userScoresListModel.getData();
    }
}
