/**
 * Toggle/Momentaryを変更するためのToggleスイッチ用のクラス
 * ToggleモードではTabキーを一度押したらクリック開始、再度押したらクリック終了
 * MomenaryモードではTabキーを押している間だけクリックを行う。
 */

package com.app.ui;

import java.awt.event.ItemEvent;
import javax.swing.JToggleButton;
import java.util.function.Consumer;

class ToggleOrMomentary extends JToggleButton{

    private Consumer<Boolean> setter;

    ToggleOrMomentary(){
        super();
        setText("Toggle");
        addItemListener(this::itemStateChanged);
    }

    void addSetter(Consumer<Boolean> setter){
        this.setter = setter;
    }

    private void itemStateChanged(ItemEvent e) {
        boolean isSelected = isSelected();
        setText(isSelected ? "Momentary" : "Toggle");
        setter.accept(isSelected);
    }

}
