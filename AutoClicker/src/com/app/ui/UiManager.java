package com.app.ui;

import com.app.controller.*;

public class UiManager implements ModeChangerRegistable{

    private static UiManager manager;

    private AppFrame frame;
    private ControlPanel controlPanel;
    private ImagePanel imagePanel;
    private UneditableText inputFieldExplane;
    private UneditableText toggleExplane;
    private IntervalInputArea inputArea;
    private ToggleOrMomentary toggleSwitch;
    private AutoClickToggle leftClickToggle;
    private AutoClickToggle rightClickToggle;


    private UiManager(){
        frame = new AppFrame();
        controlPanel = new ControlPanel();
        imagePanel = new ImagePanel();
        inputFieldExplane = new UneditableText("Interval");
        toggleExplane = new UneditableText("Toggle/Momentary");
        inputArea = new IntervalInputArea();
        toggleSwitch = new ToggleOrMomentary();
        leftClickToggle = new AutoClickToggle();
        rightClickToggle = new AutoClickToggle();
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
