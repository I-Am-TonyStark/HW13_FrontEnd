package com.mamalimomen.models;

import com.mamalimomen.base.models.BaseModel;
import com.mamalimomen.dtos.PostDTO;

import java.util.List;

public interface PostModel extends BaseModel<PostDTO> {
    List<PostDTO> findAllPosts();

    List<PostDTO> findManyPostsByAccountUsername(String username);
}