package com.app.ui;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.util.function.Consumer;

class IntervalInputArea extends JTextField{

    Consumer<Integer> setter;

    IntervalInputArea(){
        super("100", 4);
        setNumberFilter();
    }

    void addSetter(Consumer<Integer> setter){
        this.setter = setter;
        setInterval();
    }

    private void setInterval(){
        if(getText().isEmpty()){setter.accept(0); return;}
        setter.accept(Integer.parseInt(getText()));
    }

    private void setNumberFilter(){
        ((AbstractDocument)getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[0-9]*")) {
                    super.insertString(fb, offset, string, attr);
                    setInterval();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[0-9]*")) {
                    super.replace(fb, offset, length, text, attrs);
                    setInterval();
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
                super.remove(fb, offset, length);
                    setInterval();
            }
        });
    }

}
