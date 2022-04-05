package view.common;

import javax.swing.*;

public class MultiLineLabel extends JTextArea {
    public MultiLineLabel() {
        super();
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
    }
}
