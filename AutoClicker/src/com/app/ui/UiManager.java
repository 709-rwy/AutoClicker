/**
 * ui関連(swing)のオブジェクトを生成し、委譲関係を作成して連携させるためのクラス。
 */

package com.app.ui;

import javax.swing.JLabel;
import com.app.controller.*;

public class UiManager implements ModeChangerRegistable{

    private static UiManager manager;

    private AppFrame frame;
    private ControlPanel controlPanel;
    private ImagePanel imagePanel;
    private JLabel inputFieldExplane;
    private JLabel toggleExplane;
    private IntervalInputArea inputArea;
    private ToggleOrMomentary toggleSwitch;
    private AutoClickToggle leftClickToggle;
    private AutoClickToggle rightClickToggle;


    private UiManager(){
        frame = new AppFrame();
        controlPanel = new ControlPanel();
        imagePanel = new ImagePanel();
        inputFieldExplane = new JLabel("Interval");
        toggleExplane = new JLabel("Toggle/Momentary");
        inputArea = new IntervalInputArea();
        toggleSwitch = new ToggleOrMomentary();
        leftClickToggle = new AutoClickToggle(false);
        rightClickToggle = new AutoClickToggle(true);
    }

    public static UiManager getManager(){
        if(manager == null){
            manager = new UiManager();
        }
        return manager;
    }

    public void setup(){
        frame.addAll(controlPanel, imagePanel);
        controlPanel.addAll(inputFieldExplane, inputArea, toggleExplane, toggleSwitch);
        imagePanel.addAll(leftClickToggle, rightClickToggle);
        frame.setVisible(true);
    }

    @Override
    public void registerModeChanger(ModeChanger modeChanger){
        leftClickToggle.addSetter(modeChanger::setLeftClickIsValid);
        rightClickToggle.addSetter(modeChanger::setRightClickIsValid);
        toggleSwitch.addSetter(modeChanger::setIsMomentary);
        inputArea.addSetter(modeChanger::setInterval);
    }

    public void setEditable(boolean boo){
        inputArea.setEditable(boo);
        toggleSwitch.setEnabled(boo);
        rightClickToggle.setEnabled(boo);
        leftClickToggle.setEnabled(boo);
    }

}
