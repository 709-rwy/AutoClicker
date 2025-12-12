package com.app.controller;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.AWTException;


public class AutoClicker extends Robot implements ModeChangerRegistable{



    private Thread clickerThread;
    private ModeChanger modeChanger;

    public AutoClicker() throws AWTException{
        super();
    }

    void startClick(){
        this.clickerThread = new Thread(this::run);
        clickerThread.start();
    }

    @Override
    public void registerModeChanger(ModeChanger modeChanger){
        this.modeChanger = modeChanger;
    }

    private void run() {
        int interval = modeChanger.getInterval();
        boolean rightClickIsValid = modeChanger.getrightClickIsValid();
        boolean leftClickIsValid = modeChanger.getLeftClickIsValid();

        while(modeChanger.getClickerIsRunning()){
            if(rightClickIsValid){
                mousePress(InputEvent.BUTTON3_DOWN_MASK);
                mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            }
            if(leftClickIsValid){
                mousePress(InputEvent.BUTTON1_DOWN_MASK);
                mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
            }
            try{
                Thread.sleep(interval);
            }catch(InterruptedException e){
                modeChanger.stopClicker();
            }
        }

    }


}
