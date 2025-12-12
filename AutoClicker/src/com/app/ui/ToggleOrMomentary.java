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
