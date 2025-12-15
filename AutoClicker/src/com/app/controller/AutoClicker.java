/**
 *ModeChangerからアプリの状態の変更を受け取って、オートクリックを行うクラス。
 *オートクリックを前提として作られたアプリなのでクリック専用のクラスをRobotクラスの継承により作った。
 */

package com.app.controller;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.AWTException;


public class AutoClicker extends Robot implements ModeChangerRegistable{



    //ユーザーの入力、特にTabキーの入力を受け付けるためクリッカーの動作は別スレッドで行う。
    private Thread clickerThread;
    private ModeChanger modeChanger;

    public AutoClicker() throws AWTException{
        super();
    }

    void startClick(){
        //動作を単純にするためスレッドはクリック開始ごとに作成する
        this.clickerThread = new Thread(this::run);
        clickerThread.start();
    }

    //AppManagerを通してModeChangerを登録するメソッド
    @Override
    public void registerModeChanger(ModeChanger modeChanger){
        this.modeChanger = modeChanger;
    }

    //オートクリック開始時に実際に実行されるメソッド
    private void run() {
        //オートクリック中にintervalの変更を受け入れて意図しない動作が行われることを防ぐためにローカル変数で制御
        int interval = modeChanger.getInterval();
        boolean rightClickIsValid = modeChanger.getrightClickIsValid();
        boolean leftClickIsValid = modeChanger.getLeftClickIsValid();

        while(modeChanger.getClickerIsRunning()){
            //右クリック処理
            if(rightClickIsValid){
                mousePress(InputEvent.BUTTON3_DOWN_MASK);
                mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
            }
            //左クリック処理
            if(leftClickIsValid){
                mousePress(InputEvent.BUTTON1_DOWN_MASK);
                mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
            }
            
            try{
                //intervalミリ秒動作を停止させる
                //他の動作にかかる時間を考慮していないのであくまでクリック間隔の目安
                Thread.sleep(interval);
            }catch(InterruptedException e){
                // スレッド割り込み時はクリック処理を停止する
                modeChanger.stopClicker();
            }
        }

    }


}


