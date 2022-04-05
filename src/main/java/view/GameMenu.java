package view;

import controller.Command;
import controller.IClient;

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

        start.addActionListener(e -> tetrisClient.execute(Command.NewGame));
        pause.addActionListener(e -> tetrisClient.execute(Command.Stop));
        resume.addActionListener(e -> tetrisClient.execute(Command.Continue));
        about.addActionListener(e -> tetrisClient.execute(Command.About));
        highScores.addActionListener(e -> tetrisClient.execute(Command.HighScores));
    }

}
