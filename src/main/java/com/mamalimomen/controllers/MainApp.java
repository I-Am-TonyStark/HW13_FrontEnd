package com.mamalimomen.controllers;

import com.mamalimomen.controllers.utilities.AppManager;

public final class MainApp {
    private MainApp() {
    }

    public static void main(String... args) {
        AppManager.startApp();
        AppManager.endApp();
    }
}
