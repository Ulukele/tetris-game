package view;

import common.ISubscriber;
import common.Model;
import tetris.GameState;
import tetris.GameStates;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class StateLabel extends JTextArea implements ISubscriber {
    private Model<GameStates> gameStatesModel;

    public StateLabel(Font font) {
        super();
        setEditable(false);
        setCursor(null);
        setOpaque(false);
        setFocusable(false);
        setWrapStyleWord(true);
        setLineWrap(true);
        setAlignmentY(JLabel.CENTER_ALIGNMENT);

        setFont(font);
        setForeground(new Color(200, 200, 200));
    }

    public void setGameStatesModel(Model<GameStates> gameStatesModel) {
        this.gameStatesModel = gameStatesModel;
        gameStatesModel.addSubscriber(this);
        reactOnNotify();
    }

    @Override
    public void reactOnNotify() {
        GameStates state = gameStatesModel.getData();
        if (state == GameStates.Lose) {
            setText("GAME OVER\n\nPRESS R TO RESTART");
        } else if (state == GameStates.Pause) {
            setText("PAUSE\n\nPRESS ENTER TO CONTINUE");
        } else {
            setText("");
        }
    }
}
