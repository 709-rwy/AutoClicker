/**
 *ModeChangerからアプリの状態の変更を受け取って、オートクリックを行うクラスです。
 *オートクリックを前提として作られたアプリなのでクリック専用のクラスをRobotクラスの継承により作りました。
 */

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
        //ユーザーの入力、Tabキーの入力を受け付けるためクリッカーの動作は別スレッドで行う。
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
                // スレッド割り込み時はクリック処理を停止する
                modeChanger.stopClicker();
            }
        }

    }


}
