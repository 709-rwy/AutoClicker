/**
 * アプリの設定画面用のパネルクラス
 * IntervalInputAreaとToggleOrMomentaryを追加することを想定している。
 */

package com.app.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;

class ControlPanel extends JPanel implements Addable{
    ControlPanel(){
        super();
        setBounds(0, 0, 240, 150);
        setLayout(new GridLayout(2, 2));
    }
}
