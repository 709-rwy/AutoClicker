package com.app.ui;

import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;

public interface Addable {
    default void addAll(Component... components){
        Arrays.stream(components).forEach(((Container)this)::add);
    }
}
