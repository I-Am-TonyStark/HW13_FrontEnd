package com.mamalimomen.views.impl;

import com.mamalimomen.base.controllers.guis.DialogProvider;
import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.controllers.utilities.InValidDataException;
import com.mamalimomen.base.controllers.utilities.SingletonScanner;
import com.mamalimomen.base.views.impl.BaseViewImpl;
import com.mamalimomen.dtos.AccountDTO;
import com.mamalimomen.models.PostModel;
import com.mamalimomen.dtos.PostDTO;
import com.mamalimomen.models.impl.PostModelImpl;
import com.mamalimomen.views.PostView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PostViewImpl extends BaseViewImpl<PostDTO, PostModel> implements PostView {

    public PostViewImpl(ClientManager cm) {
        super(new PostModelImpl(cm));
    }

    @Override
    public Optional<PostDTO> createNewPost(AccountDTO account) {
        PostDTO post = new PostDTO();
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s", "Text: ");
                String text = SingletonScanner.readParagraph();
                if (text.equalsIgnoreCase("esc")) {
                    break;
                }
                post.setText(text);

                post.setInsertDate(new Date(System.currentTimeMillis()));
                post.setAccount(account);

                DialogProvider.createAndShowTerminalMessage("%s%n", model.saveOne(post));
                return Optional.of(post);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
        return Optional.empty();
    }

    @Override
    public List<PostDTO> retrieveManyExistPosts(AccountDTO account) {
        return model.findManyPostsByAccountUsername(account.getUser().getUsername());
    }

    @Override
    public List<PostDTO> retrieveAllExistPosts() {
        return model.findAllPosts();
    }

    @Override
    public String updateExistPost(PostDTO post) {
        while (true) {
            try {
                DialogProvider.createAndShowTerminalMessage("%s (old = %s): ", "New Text", post.getText());
                String newText = SingletonScanner.readParagraph();
                if (newText.equalsIgnoreCase("esc")) {
                    break;
                }
                post.setText(newText);

                return model.updateOne(post);
            } catch (InValidDataException e) {
                DialogProvider.createAndShowTerminalMessage("%s %s%s%n", "Wrong entered data format for", e.getMessage(), "!");
            }
        }
        return "You Cancelled this operation!";
    }

    @Override
    public String deleteExistPost(PostDTO post) {
        DialogProvider.createAndShowTerminalMessage("%s", "Do you want to delete this post? ");
        String answer = SingletonScanner.readLine();

        if (answer.equals("YES")) {
            return model.deleteOne(post);
        }
        return "You Cancelled this operation!";
    }
}
