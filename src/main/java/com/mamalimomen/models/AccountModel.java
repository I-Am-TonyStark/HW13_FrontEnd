package com.mamalimomen.models;

import com.mamalimomen.base.models.BaseModel;
import com.mamalimomen.dtos.AccountDTO;

import java.util.Optional;

public interface AccountModel extends BaseModel<AccountDTO> {

    Optional<AccountDTO> findOneActiveAccountByUsername(String username);
}
