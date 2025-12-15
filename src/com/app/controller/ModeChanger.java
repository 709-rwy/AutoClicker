/**
 * アプリの状態の変更操作を受け取り、
 * その記録とAutoClickerへの操作を行うクラス
 * 
 */

package com.app.controller;

import com.app.ui.UiManager;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class ModeChanger {

    private int interval;
    private int triggerKey;
    private boolean isMomentary;
    private boolean rightClickIsValid;
    private boolean leftClickIsValid;
    private boolean clickerIsRunning;

    private AutoClicker autoClicker;

    public ModeChanger(){
        triggerKey = NativeKeyEvent.VC_TAB;
    }

    //KeyLisnerに渡してオートクリッカーの制御を行わせるためのメソッド
    public void acceptTriggerKeyPressed(){
        if(clickerIsRunning && (!isMomentary)){
            stopClicker();
            return;
        }
        startClicker();
    }

    public void acceptTriggerKeyReleased(){
        if(isMomentary){
            stopClicker();
        }
    }

    public void startClicker(){
        UiManager.getManager().setEditable(false);
        clickerIsRunning = true;
        autoClicker.startClick();
    }

    public void stopClicker(){
        UiManager.getManager().setEditable(true);
        clickerIsRunning = false;
    }

    //IntervalInputAreaの入力を受け付けるためにこのメソッドをAppManager,UiManagerを通じて渡す
    public void setInterval(int interval){
        this.interval = interval;
    }

    
    public void setClickerIsRunning(boolean clickerIsRunning){
        this.clickerIsRunning = clickerIsRunning;
    }

    public void setIsMomentary(boolean isMomentary){
        this.isMomentary = isMomentary;
    }

    public void setRightClickIsValid(boolean rightClickIsValid){
        this.rightClickIsValid = rightClickIsValid;
    }

    public void setLeftClickIsValid(boolean leftClickIsValid){
        this.leftClickIsValid = leftClickIsValid;
    }

    public void regiterAutoClicker(AutoClicker autoClicker){
        this.autoClicker = autoClicker;
    }

    public void registerModeChangerAll(ModeChangerRegistable... objects){
        for(var object : objects){
            object.registerModeChanger(this);
        }
    }

    public int getInterval(){
        return interval;
    }

    public int getTriggerKey(){
        return triggerKey;
    }

    public boolean getIsMomentary(){
        return isMomentary;
    }

    public boolean getrightClickIsValid(){
        return rightClickIsValid;
    }

    public boolean getLeftClickIsValid(){
        return leftClickIsValid;
    }

    public boolean getClickerIsRunning(){
        return clickerIsRunning;
    }

}