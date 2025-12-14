/**
 * アプリのエントリークラス
 */

package com.app.entry;

import com.app.manager.AppManager;

public class App {
    public static void main(String[] args) throws Exception {
        AppManager manager = AppManager.getManager();
        if(manager != null){
            manager.appStart();
        }else{
            System.out.println("Error");
        }
    }
}
