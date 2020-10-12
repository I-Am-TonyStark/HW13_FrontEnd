package com.mamalimomen.views;

import com.mamalimomen.base.views.BaseView;
import com.mamalimomen.dtos.AccountDTO;

import java.util.Optional;

public interface AccountView extends BaseView<AccountDTO> {
    Optional<AccountDTO> createNewAccount();

    Optional<AccountDTO> retrieveExistActiveAccount();

    String updateExistActiveAccountPassword(AccountDTO account);

    String updateExistActiveAccountInformation(AccountDTO account);

    String deleteExistActiveAccount(AccountDTO account);
}
