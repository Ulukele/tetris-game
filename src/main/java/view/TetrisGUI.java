package view;

import javax.swing.*;
import java.awt.*;

public class TetrisGUI extends JFrame {
    private final JLabel scoreLabel = new ScoreLabel();

    public TetrisGUI() {
        super("Tetris");
        this.setBounds(100, 100, 500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(scoreLabel);

    }
}
