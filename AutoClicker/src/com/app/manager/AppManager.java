package com.app.manager;

import com.app.ui.UiManager;
import com.app.controller.*;
import java.awt.AWTException;
import com.github.kwhat.jnativehook.NativeHookException;

public class AppManager {

    private static AppManager appManager;

    private UiManager uiManager;
    private AutoClicker autoClicker;
    private ModeChanger modeChanger;
    private KeyListener keyListener;


    private AppManager() throws AWTException, NativeHookException{
        uiManager = UiManager.getManager();
        autoClicker = new AutoClicker();
        modeChanger = new ModeChanger();
        keyListener = new KeyListener();
    }

    public static AppManager getManager(){
        if(appManager == null){
            try{
                appManager = new AppManager();
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }  
        }
        return appManager;
    }

    public void appStart(){
        modeChanger.registerModeChangerAll(uiManager, autoClicker, keyListener);
        modeChanger.regiterAutoClicker(autoClicker);
        uiManager.setup();
    }

    public boolean getAutoClickIsRunning(){
        return modeChanger.getClickerIsRunning();
    }
}
