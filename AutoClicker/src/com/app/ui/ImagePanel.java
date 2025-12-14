/**
 * マウスの画像を配置し、
 * その上にそれぞれのマウスボタンをクリックするかを配置するためのパネルクラス。
 */

package com.app.ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;


class ImagePanel extends JPanel implements Addable{

    private Image image;

    ImagePanel(){
        super();
        setBounds(0,150 , 240, 210);
        setLayout(null);
        image = new ImageIcon(getClass().getResource("/images/mouseImage.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
