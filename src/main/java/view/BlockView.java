package view;

import javax.swing.*;
import java.awt.*;

public class BlockView extends JPanel {
    private final int size;
    private Color color;

    public BlockView(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(0, 0, size, size);
        g.setColor(color);
        g.fillRect(0, 0, size, size);
    }
}
