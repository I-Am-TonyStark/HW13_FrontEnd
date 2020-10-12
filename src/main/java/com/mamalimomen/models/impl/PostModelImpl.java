package com.mamalimomen.models.impl;

import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.models.impl.BaseModelImpl;
import com.mamalimomen.dtos.AccountDTO;
import com.mamalimomen.dtos.UserDTO;
import com.mamalimomen.models.PostModel;
import com.mamalimomen.dtos.PostDTO;

import java.util.List;

public class PostModelImpl extends BaseModelImpl<PostDTO> implements PostModel {


    public PostModelImpl(ClientManager cm) {
        super(cm);
    }

    @Override
    public List<PostDTO> findAllPosts() {
        return findAll(new PostDTO());
    }

    @Override
    public List<PostDTO> findManyPostsByAccountUsername(String username) {//TODO REFACTOR MAMP BY STRING DATA
        UserDTO userDTO = new UserDTO();
        try {
            userDTO.setUsername(username);
        } catch (InValidDataException e) {
            e.printStackTrace();
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUser(userDTO);

        PostDTO postDTO = new PostDTO();
        postDTO.setAccount(accountDTO);
        return findMany(postDTO);
    }
}
