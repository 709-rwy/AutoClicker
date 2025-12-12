package com.app.ui;

import javax.swing.JPanel;
import java.awt.FlowLayout;

class ImagePanel extends JPanel implements Addable{
    ImagePanel(){
        super();
        setBounds(0,150 , 240, 210);
        setLayout(new FlowLayout());
        setBackground(java.awt.Color.LIGHT_GRAY); 
    }
}
