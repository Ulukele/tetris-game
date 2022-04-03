package view;

import view.common.MultiLineLabel;

import javax.swing.*;

public class AboutInfoPanel extends JPanel {
    private final MultiLineLabel infoLabel;

    public AboutInfoPanel() {
        super();
        infoLabel = new MultiLineLabel();
        infoLabel.setText("""
                Tetris game for NSU lab
                
                Control:
                
                W - Rotate
                
                A - Left
                
                S - Down
                
                D - Right
                
                R - Restart
                
                Enter - Pause
                """);
        add(infoLabel);
        setVisible(true);
    }

    public MultiLineLabel getInfoLabel() {
        return infoLabel;
    }
}
