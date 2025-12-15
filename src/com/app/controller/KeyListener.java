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

    //JNativeHookは数字でキーを管理しているためこちらも同様に
    //0はKEY_LOCATION_UNKNOWNに割り当てられているので、押されていない状態は-1で表す。
    int pressedKey;

    ModeChanger modeChanger;


    public KeyListener() throws NativeHookException{
        //JNativeHookからのログをなくす
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
        //キーが押されている間は繰り返しこのメソッドが実行されるため、
        //キーの押しはじめのみを取得するためにpressdeKey == -1をフラグにする
        if(pressedKey == -1){
            pressedKey = e.getKeyCode();
            if(e.getKeyCode() == modeChanger.getTriggerKey()){
                modeChanger.acceptTriggerKeyPressed();
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e){
        //キーを二つ以上押している間に片方のキーを話すとどのキーを話したかがわからないので
        //e.getKeyCode() == pressedKeyで管理
        if(e.getKeyCode() == pressedKey){
            pressedKey = -1;
            modeChanger.acceptTriggerKeyReleased();
        }
    }
}

