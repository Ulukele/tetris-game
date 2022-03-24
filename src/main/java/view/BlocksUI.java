package view;

import javax.swing.*;
import java.awt.*;

public class BlocksUI extends JPanel {
    private int width;
    private int height;

    BlocksUI(int blocksX, int blocksY, int blockSize) {
        super();
        width = blocksX * blockSize;
        height = blocksY * blockSize;
        this.setSize(width, height);
        this.setBackground(new Color(200, 250, 200));
        this.setVisible(true);
    }
}
