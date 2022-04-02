package view;

import javax.swing.*;
import java.awt.*;

public class BlockView extends JPanel {
    private final int size;
    private final int borderSize;
    private final int innerSize;
    private Color color;
    private Color borderColor;

    public BlockView(int size, Color color) {
        this.size = size;
        this.borderSize = size / 10;
        this.innerSize = size - 2 * borderSize;
        this.color = color;
        setBorderColor();
    }

    private void setBorderColor() {
        borderColor = color.darker();
    }

    public void setColor(Color color) {
        this.color = color;
        setBorderColor();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, size, size);
        g.setColor(borderColor);
        g.fillRect(0, 0, size, size);
        g.setColor(color);
        g.fillRect(borderSize, borderSize, innerSize, innerSize);
    }
}
