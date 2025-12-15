/**
 * アプリの状態変更を行うクラスが、
 * ModeChanger を登録するためのインターフェース
 */

package com.app.controller;

public interface ModeChangerRegistable {
    void registerModeChanger(ModeChanger modeChanger);
}
