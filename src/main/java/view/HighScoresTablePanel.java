package view;

import common.ISubscriber;
import common.Model;
import tetris.highScores.UserScore;

import javax.swing.*;
import java.util.List;

public class HighScoresTablePanel extends JPanel implements ISubscriber {
    private Model<List<UserScore>> userScoresListModel;
    private final JTable table;
    private final JLabel label;

    public HighScoresTablePanel(int rowsCount) {
        super();

        label = new JLabel("High-Scores");

        table = new JTable(rowsCount, 3);
        table.setEnabled(false);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(table)
        );
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addComponent(label)
                        .addComponent(table)
        );
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
        List<UserScore> userScores = userScoresListModel.getData();

        int idx = 0;
        for (final UserScore userScore : userScores) {
            table.setValueAt(userScore.getUserName(), idx, 0);
            table.setValueAt(userScore.getScore(),  idx, 1);
            table.setValueAt(userScore.getDateTime(),  idx, 2);
            idx++;
        }

        repaint();
    }
}
