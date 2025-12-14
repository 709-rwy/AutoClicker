package com.app.ui;

import java.util.function.Consumer;
import java.awt.event.ItemEvent;
import javax.swing.JToggleButton;

class AutoClickToggle extends JToggleButton {

    private Consumer<Boolean> setter;

    AutoClickToggle(){
        super();
        setText("OFF");
        addItemListener(this::itemStateChanged);
    }

    AutoClickToggle(boolean isright){
        this();
        setBounds(isright ? 40 : 140, 60, 60, 30);
    }

    AutoClickToggle(Consumer<Boolean> setter){
        this();
        addSetter(setter);
    }

    AutoClickToggle(boolean isright, Consumer<Boolean> setter){
        this(isright);
        addSetter(setter);
    }

    void addSetter(Consumer<Boolean> setter){
        this.setter = setter;
    }

    private void itemStateChanged(ItemEvent e) {
        boolean isSelected = isSelected();
        setText(isSelected ? "ON" : "OFF");
        setter.accept(isSelected);
    }

}

