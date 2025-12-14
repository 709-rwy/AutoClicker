/**
 * JanativeHookを用いてTabキーの入力を監視し
 * ModeChangerにTabキーが押された、離されたという情報をおくるクラス。
 */


package com.app.controller;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.NativeHookException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class KeyListener implements NativeKeyListener, ModeChangerRegistable{

    int pressedKey;

    ModeChanger modeChanger;


    public KeyListener() throws NativeHookException{
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackageName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(this);
        pressedKey = -1;
    }


    @Override
    public void registerModeChanger(ModeChanger modeChanger){
        this.modeChanger = modeChanger;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e){
        if(pressedKey == -1){
            pressedKey = e.getKeyCode();
            if(e.getKeyCode() == modeChanger.getTriggerKey()){
                modeChanger.acceptTriggerKeyPressed();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e){
        if(e.getKeyCode() == pressedKey){
            pressedKey = -1;
            modeChanger.acceptTriggerKeyReleased();
        }
    }
}
