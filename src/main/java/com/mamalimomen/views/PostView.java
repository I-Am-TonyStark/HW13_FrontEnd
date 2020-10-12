package com.mamalimomen.views;

import com.mamalimomen.base.views.BaseView;
import com.mamalimomen.dtos.AccountDTO;
import com.mamalimomen.dtos.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostView extends BaseView<PostDTO> {
    Optional<PostDTO> createNewPost(AccountDTO account);

    List<PostDTO> retrieveManyExistPosts(AccountDTO account);

    List<PostDTO> retrieveAllExistPosts();

    String updateExistPost(PostDTO post);

    String deleteExistPost(PostDTO post);
}
