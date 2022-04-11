package view;

import controller.IClient;
import controller.StateCommand;

import javax.swing.*;

public class GameMenu extends JMenu {

    public GameMenu(IClient tetrisClient) {
        super("Game");
        JMenuItem start = new JMenuItem("Start new");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem resume = new JMenuItem("Resume");
        JMenuItem about = new JMenuItem("About");
        JMenuItem highScores = new JMenuItem("High Scores");
        this.add(start);
        this.add(pause);
        this.add(resume);
        this.add(about);
        this.add(highScores);

        start.addActionListener(e -> tetrisClient.execute(StateCommand.NewGame));
        pause.addActionListener(e -> tetrisClient.execute(StateCommand.Stop));
        resume.addActionListener(e -> tetrisClient.execute(StateCommand.Continue));
        about.addActionListener(e -> tetrisClient.execute(StateCommand.About));
        highScores.addActionListener(e -> tetrisClient.execute(StateCommand.HighScores));
    }

}
