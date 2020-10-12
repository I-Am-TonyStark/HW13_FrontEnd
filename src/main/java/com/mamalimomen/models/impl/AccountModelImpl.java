package com.mamalimomen.models.impl;

import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.models.impl.BaseModelImpl;
import com.mamalimomen.dtos.UserDTO;
import com.mamalimomen.models.AccountModel;
import com.mamalimomen.dtos.AccountDTO;

import java.util.Optional;


public class AccountModelImpl extends BaseModelImpl<AccountDTO> implements AccountModel {

    public AccountModelImpl(ClientManager cm) {
        super(cm);
    }

    @Override
    public Optional<AccountDTO> findOneActiveAccountByUsername(String username) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO.setUsername(username);
        } catch (InValidDataException e) {
            e.printStackTrace();
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUser(userDTO);

        return findOne(accountDTO);
    }
}
