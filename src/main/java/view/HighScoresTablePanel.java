package view;

import common.ISubscriber;
import common.Model;
import tetris.highScores.UserScore;

import javax.swing.*;
import java.util.List;

public class HighScoresTablePanel extends JPanel implements ISubscriber {
    private Model<List<UserScore>> userScoresListModel;
    private List<UserScore> userScores;
    private final JTable table;
    private final JLabel label;

    public HighScoresTablePanel() {
        super();

        label = new JLabel("High-Scores");

        String[] columnNames = {"Time", "User", "Score"};
        String[][] data = {};
        table = new JTable(data, columnNames);

        table.setEnabled(false);

        add(label);
        add(table);
    }

    public JTable getTable() {
        return table;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setUserScoresListModel(Model<List<UserScore>> userScoresListModel) {
        this.userScoresListModel = userScoresListModel;
        userScoresListModel.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() {
        userScores = userScoresListModel.getData();

        repaint();
    }
}
