package com.mamalimomen.controllers.utilities;

import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.dtos.BaseDTO;
import com.mamalimomen.base.views.BaseView;
import com.mamalimomen.views.impl.AccountViewImpl;
import com.mamalimomen.views.impl.PostViewImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AppManager {
    private static final List<ClientManager> cmList = new ArrayList<>();
    private static final Map<Views, BaseView<? extends BaseDTO>> viewMapper = new HashMap<>();

    private AppManager() {
    }

    public static synchronized void startApp() {
        ClientManager cm = new ClientManager();

        cmList.add(cm);

        viewMapper.put(Views.ACCOUNT_VIEW, new AccountViewImpl(cm));
        viewMapper.put(Views.POST_VIEW, new PostViewImpl(cm));

        MenuFactory.getMenu(null).routerMenu();
    }

    public static synchronized <D extends BaseDTO, S extends BaseView<D>> S getView(Views view) {
        return (S) viewMapper.get(view);
    }

    public static synchronized void endApp() {
        for (ClientManager em : cmList) {
            em.turnOffClient();
        }
    }
}
