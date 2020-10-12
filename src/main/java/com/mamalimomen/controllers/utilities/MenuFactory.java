package com.mamalimomen.controllers.utilities;

import com.mamalimomen.base.controllers.menus.Menu;
import com.mamalimomen.controllers.menus.MainMenu;
import com.mamalimomen.controllers.menus.UserMenu;
import com.mamalimomen.dtos.AccountDTO;

public final class MenuFactory {
    private MenuFactory() {
    }

    public static synchronized <A extends AccountDTO> Menu getMenu(A account) {
        if (account instanceof AccountDTO) {
            return new UserMenu<>(account);
        } else return new MainMenu<>();
    }
}