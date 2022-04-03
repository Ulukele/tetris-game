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
        this.add(start);
        this.add(pause);
        this.add(resume);
        this.add(about);

        start.addActionListener(e -> tetrisClient.execute(Command.NewGame));
        pause.addActionListener(e -> tetrisClient.execute(Command.Stop));
        resume.addActionListener(e -> tetrisClient.execute(Command.Continue));
        about.addActionListener(e -> tetrisClient.execute(Command.About));
    }

}
