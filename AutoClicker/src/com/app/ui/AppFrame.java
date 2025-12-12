package com.app.ui;

import javax.swing.JFrame;

class AppFrame extends JFrame implements Addable{
    AppFrame(){
        super("連打ツール");
        setSize(240, 360);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
