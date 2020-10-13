package com.mamalimomen.controllers.menus;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.controllers.utilities.AppManager;
import com.mamalimomen.controllers.utilities.MenuFactory;
import com.mamalimomen.controllers.utilities.Views;
import com.mamalimomen.dtos.AccountDTO;
import com.mamalimomen.dtos.PostDTO;
import com.mamalimomen.views.AccountView;
import com.mamalimomen.views.PostView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public final class Menus {
    private Menus() {
    }

    static void login() {
        while (true) {
            DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "LOGIN");
            AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);
            Optional<AccountDTO> oAccount = accountView.retrieveExistActiveAccount();

            if (oAccount.isEmpty()) {
                DialogProvider.createAndShowTerminalMessage("%s%n%s", "There is not any Account with this username!", "Do you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y")) {
                    break;
                }
                continue;
            }
            AccountDTO account = oAccount.get();

            DialogProvider.createAndShowTerminalMessage("%s", "Password: ");
            String password = SingletonScanner.readLine();
            if (SecurityManager.checkPasswordHash(password, account.getUser().getPassword())) {
                MenuFactory.getMenu(account).routerMenu();
                break;
            } else {
                DialogProvider.createAndShowTerminalMessage("%s%n", "Wrong Password!");
            }
        }
    }

    static void signUp() {
        while (true) {
            DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "SIGN UP");
            AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);

            Optional<AccountDTO> oAccount = accountView.createNewAccount();
            if (oAccount.isPresent()) {
                DialogProvider.createAndShowTerminalMessage("%s%n", "Your Account was created successfully!");
                MenuFactory.getMenu(oAccount.get()).routerMenu();
                break;
            } else {
                DialogProvider.createAndShowTerminalMessage("%s%n%s", "Can not create your account!", "Do you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
            }
        }
    }

    static <A extends AccountDTO> void changeYourPassword(A account) {
        DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "CHANGE YOUR PASSWORD");
        AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);
        DialogProvider.createAndShowTerminalMessage("%s%n", accountView.updateExistActiveAccountPassword(account));
    }

    public static <A extends AccountDTO> void changeYourInformation(A account) {
        DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "CHANGE YOUR INFORMATION");
        AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);
        DialogProvider.createAndShowTerminalMessage("%s%n", accountView.updateExistActiveAccountInformation(account));
    }

    public static <A extends AccountDTO> void seeYourPosts(A account) {
        while (true) {
            PostView postView = AppManager.getView(Views.POST_VIEW);
            List<PostDTO> posts = postView.retrieveManyExistPosts(account);

            if (posts.isEmpty()) {
                DialogProvider.createAndShowTerminalMessage("%s%n", "You have no post yet!");
                break;
            }

            while (true) {
                DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "SEE YOUR POSTS");
                try {
                    for (int i = 1; i <= posts.size(); i++) {
                        DialogProvider.createAndShowTerminalMessage("%d. %s%n", i, posts.get(i - 1));
                    }
                    DialogProvider.createAndShowTerminalMessage("%s", "Enter your choice (or other number for \"exit\"): ");
                    int choice = SingletonScanner.readInteger();
                    DialogProvider.createAndShowTerminalMessage("%s%n", postView.updateExistPost(posts.get(choice - 1)));
                } catch (InputMismatchException e) {
                    DialogProvider.createAndShowTerminalMessage("%s%n", "Wrong format, enter an integer number please!");
                    SingletonScanner.clearBuffer();
                } catch (IndexOutOfBoundsException e) {
                    return;
                }
            }
        }

    }

    public static <A extends AccountDTO> void insertNewPost(A account) {
        while (true) {
            DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "INSERT NEW POST");
            PostView postView = AppManager.getView(Views.POST_VIEW);

            Optional<PostDTO> oPost = postView.createNewPost(account);
            if (oPost.isPresent()) {
                DialogProvider.createAndShowTerminalMessage("%s%n", "Your Post was created successfully!");
                break;
            } else {
                DialogProvider.createAndShowTerminalMessage("%s%n%s", "Can not create your Post!", "Do you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y"))
                    break;
            }
        }
    }

    public static <A extends AccountDTO> void seeNewPosts(A account) {
        DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "SEE NEW POSTS");
        PostView postView = AppManager.getView(Views.POST_VIEW);
        List<PostDTO> posts = postView.retrieveAllExistPosts();

        if (posts.isEmpty()) {
            DialogProvider.createAndShowTerminalMessage("%s%n", "There is not any post yet!");
        }
        for (PostDTO post : posts) {
            DialogProvider.createAndShowTerminalMessage("%s%n", post);
        }
    }

    public static <A extends AccountDTO> void seeAnAccount(A account) {
        while (true) {
            DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "SEARCH AN ACCOUNT");
            AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);
            Optional<AccountDTO> oAccount = accountView.retrieveExistActiveAccount();

            if (oAccount.isEmpty()) {
                DialogProvider.createAndShowTerminalMessage("%s%n%s", "There is not any Account with this username!", "Do you want try again (y/n)? ");
                String answer = SingletonScanner.readLine();
                if (!answer.equalsIgnoreCase("y")) {
                    break;
                }
                continue;
            }
            AccountDTO searchedAccount = oAccount.get();

            DialogProvider.createAndShowTerminalMessage("%s%n", searchedAccount);

            PostView postView = AppManager.getView(Views.POST_VIEW);
            List<PostDTO> posts = postView.retrieveManyExistPosts(searchedAccount);

            if (posts.isEmpty()) {
                DialogProvider.createAndShowTerminalMessage("%s%n", "This account has no post yet!");
                break;
            }
            for (PostDTO post : posts) {
                DialogProvider.createAndShowTerminalMessage("%s%n", post);
            }
            break;
        }
    }

    public static <A extends AccountDTO> void deleteYourAccount(A account) {
        DialogProvider.createAndShowTerminalMessage("%n====== %s ======%n", "DELETE YOUR ACCOUNT");
        AccountView accountView = AppManager.getView(Views.ACCOUNT_VIEW);
        DialogProvider.createAndShowTerminalMessage("%s%n", accountView.deleteExistActiveAccount(account));
    }
}
