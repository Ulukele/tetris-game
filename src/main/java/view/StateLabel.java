package view;

import common.ISubscriber;
import common.Model;
import tetris.GameStates;
import view.common.MultiLineLabel;

import javax.swing.*;
import java.awt.*;

public class StateLabel extends MultiLineLabel implements ISubscriber {
    private Model<GameStates> gameStatesModel;

    public StateLabel() {
        super();
        setAlignmentY(JLabel.CENTER_ALIGNMENT);
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
        } else if (state != GameStates.Playing) {
            setText("PAUSE\n\nPRESS ENTER TO CONTINUE");
        } else {
            setText("");
        }
    }
}
