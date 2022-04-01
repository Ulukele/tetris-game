package view;

import controller.Command;
import controller.IClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JMenu {

    IClient tetrisClient;

    public GameMenu(IClient tetrisClient) {
        super("Game");
        this.tetrisClient = tetrisClient;
        JMenuItem start = new JMenuItem("Start new");
        JMenuItem pause = new JMenuItem("Pause");
        JMenuItem resume = new JMenuItem("Resume");
        this.add(start);
        this.add(resume);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisClient.execute(Command.NewGame);
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisClient.execute(Command.Stop);
            }
        });

        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisClient.execute(Command.Continue);
            }
        });
    }

}
