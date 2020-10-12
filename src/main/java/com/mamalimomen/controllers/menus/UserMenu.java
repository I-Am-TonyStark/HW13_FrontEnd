package com.mamalimomen.controllers.menus;

import com.mamalimomen.dtos.AccountDTO;

public class UserMenu<A extends AccountDTO> extends AbstractMenu<A> {
    public UserMenu(A account) {
        super(account.getUser().getFullName() + "'s menu",
                account,
                "Change your password",
                "Change your information",
                "See your Posts",
                "Insert new Post",
                "See new Posts",
                "See an Account",
                "Delete your Account"
        );
    }

    @Override
    public void routerMenu() {
        while (getAccount() != null) {
            switch (showMenu()) {
                case 1:
                    Menus.changeYourPassword(getAccount());
                    break;
                case 2:
                    Menus.changeYourInformation(getAccount());
                    break;
                case 3:
                    Menus.seeYourPosts(getAccount());
                    break;
                case 4:
                    Menus.insertNewPost(getAccount());
                    break;
                case 5:
                    Menus.seeNewPosts(getAccount());
                    break;
                case 6:
                    Menus.seeAnAccount(getAccount());
                    break;
                case 7:
                    Menus.deleteYourAccount(getAccount());
                    break;
                default:
                    return;
            }
        }
    }
}
