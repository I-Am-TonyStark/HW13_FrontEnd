package com.mamalimomen.views.impl;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SecurityManager;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.views.impl.BaseViewImpl;
import com.mamalimomen.dtos.UserDTO;
import com.mamalimomen.models.AccountModel;
import com.mamalimomen.dtos.AccountDTO;
import com.mamalimomen.models.impl.AccountModelImpl;
import com.mamalimomen.views.AccountView;

import java.util.Optional;

public class AccountViewImpl extends BaseViewImpl<AccountDTO, AccountModel> implements AccountView {

    public AccountViewImpl(ClientManager cm) {
        super(new AccountModelImpl(cm));
    }

    @Override
    public Optional<AccountDTO> createNewAccount() {
        UserDTO user = new UserDTO();
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s", "First Name: ");
                String firstName = SingletonScanner.readLine();
                if (firstName.equalsIgnoreCase("esc")) {
                    break;
                }
                user.setFirstName(firstName);

                DialogProvider.createAndShowTerminalMessage("%s", "Last Name: ");
                user.setLastName(SingletonScanner.readLine());

                DialogProvider.createAndShowTerminalMessage("%s", "About You: ");
                user.setAboutMe(SingletonScanner.readParagraph());

                DialogProvider.createAndShowTerminalMessage("%s", "Password: ");
                user.setAndHashPassword(SingletonScanner.readLine());

                DialogProvider.createAndShowTerminalMessage("%s", "Username: ");
                String username = SingletonScanner.readLine();
                user.setUsername(username);
                if (model.findOneActiveAccountByUsername(username).isPresent()) {
                    DialogProvider.createAndShowTerminalMessage("%s%n", "This Username has taken already!");
                    continue;
                }

                AccountDTO account = new AccountDTO();
                account.setUser(user);

                DialogProvider.createAndShowTerminalMessage("%s%n", model.saveOne(account));
                return Optional.of(account);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountDTO> retrieveExistActiveAccount() {
        UserDTO user = new UserDTO();
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s", "Username: ");
                String username = SingletonScanner.readLine();
                user.setUsername(username);
                return model.findOneActiveAccountByUsername(username);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
    }

    @Override
    public String updateExistActiveAccountPassword(AccountDTO account) {
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s", "Old Password: ");
                String oldPassword = SingletonScanner.readLine();
                if (oldPassword.equalsIgnoreCase("esc")) {
                    break;
                } else if (!SecurityManager.checkPasswordHash(oldPassword, account.getUser().getPassword())) {
                    DialogProvider.createAndShowTerminalMessage("%s%n%n", "Wrong Password!");
                    continue;
                }
                DialogProvider.createAndShowTerminalMessage("%s", "New Password: ");
                String newPassword = SingletonScanner.readLine();
                if (newPassword.equalsIgnoreCase("esc")) {
                    break;
                }
                account.getUser().setAndHashPassword(newPassword);

                return model.updateOne(account);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String updateExistActiveAccountInformation(AccountDTO account) {
        UserDTO user = account.getUser().copy();
        outer:
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s (old = %s): ", "New First Name", user.getFirstName());
                String newFirstName = SingletonScanner.readLine();
                if (newFirstName.equalsIgnoreCase("esc")) {
                    break;
                }
                if (!newFirstName.equalsIgnoreCase("pass")) {
                    user.setFirstName(newFirstName);
                }

                DialogProvider.createAndShowTerminalMessage("%s (old = %s): ", "New Last Name", user.getLastName());
                String newLastName = SingletonScanner.readLine();
                if (newLastName.equalsIgnoreCase("esc")) {
                    break;
                }
                if (!newLastName.equalsIgnoreCase("pass")) {
                    user.setLastName(newLastName);
                }

                DialogProvider.createAndShowTerminalMessage("%s (old = %s): ", "New About You", user.getAboutMe());
                String newAboutMe = SingletonScanner.readParagraph();
                if (newAboutMe.equalsIgnoreCase("esc")) {
                    break;
                }
                if (!newAboutMe.equalsIgnoreCase("pass")) {
                    user.setAboutMe(newAboutMe);
                }

                while (true) {
                    DialogProvider.createAndShowTerminalMessage("%s (old = %s): ", "New Username", user.getUsername());
                    String newUsername = SingletonScanner.readLine();
                    if (newUsername.equalsIgnoreCase("esc")) {
                        break outer;
                    }
                    user.setUsername(newUsername);
                    if (model.findOneActiveAccountByUsername(newUsername).isPresent()) {
                        DialogProvider.createAndShowTerminalMessage("%s%n", "This Username has taken already!");
                    } else {
                        break;
                    }
                }

                account.setUser(user);
                return model.updateOne(account);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String deleteExistActiveAccount(AccountDTO account) {
        DialogProvider.createAndShowTerminalMessage("%s", "Do you want to delete your account? ");
        String answer = SingletonScanner.readLine();

        if (answer.equals("YES")) {
            String result = model.deleteOne(account);
            account = null;
            return result;
        }
        return "You Cancelled this operation!";
    }
}
