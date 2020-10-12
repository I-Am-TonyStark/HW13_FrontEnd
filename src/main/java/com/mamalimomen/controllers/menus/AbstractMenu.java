package com.mamalimomen.controllers.menus;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.controllers.menus.Menu;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.dtos.AccountDTO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public abstract class AbstractMenu<A extends AccountDTO> implements Menu {
    private final A account;
    private final String title;
    private final List<String> options;

    public AbstractMenu(String title, A account, String... options) {
        this.options = new ArrayList<>(List.of(options));
        this.title = title;
        this.account = account;
    }

    public A getAccount() {
        return this.account;
    }

    @Override
    public int showMenu() {
        while (true) {
            DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", title.toUpperCase());
            for (int i = 1; i <= options.size(); i++) {
                DialogProvider.createAndShowTerminalMessage("%d. %s%n", i, options.get(i - 1));
            }
            DialogProvider.createAndShowTerminalMessage("%s", "Enter your choice (or other number for \"exit\"): ");
            try {
                return SingletonScanner.readInteger();
            } catch (InputMismatchException e) {
                DialogProvider.createAndShowTerminalMessage("%s%n", "Wrong format, enter an integer number please!");
                SingletonScanner.clearBuffer();
            }
        }
    }
}