package com.mamalimomen.base.models.impl;

import com.mamalimomen.base.controllers.utilities.ClientManager;
import com.mamalimomen.base.controllers.utilities.Command;
import com.mamalimomen.base.controllers.utilities.Count;
import com.mamalimomen.base.controllers.utilities.MAMP;
import com.mamalimomen.base.dtos.BaseDTO;
import com.mamalimomen.base.models.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseModelImpl<D extends BaseDTO<Long>> implements BaseModel<D> {
    private final ClientManager cm;

    public BaseModelImpl(ClientManager cm) {
        this.cm = cm;
    }

    @Override
    public void closeClientManger() {
        cm.turnOffClient();
    }

    @Override
    public String saveOne(D d) {
        return (String) cm.sendRequestAndGetAnswer(new MAMP<>(Command.CREATE, Count.ONE, d));
    }

    @Override
    public String saveMany(List<D> l) {
        return null;
    }

    @Override
    public String updateOne(D d) {
        return (String) cm.sendRequestAndGetAnswer(new MAMP<>(Command.UPDATE, Count.ONE, d));
    }

    @Override
    public String updateMany(List<D> l) {
        return null;
    }

    @Override
    public String deleteOne(D d) {
        return (String) cm.sendRequestAndGetAnswer(new MAMP<>(Command.DELETE, Count.ONE, d));
    }

    @Override
    public String deleteMany(List<D> l) {
        return null;
    }

    @Override
    public Optional<D> findOne(D d) {
        Object obj = cm.sendRequestAndGetAnswer(new MAMP<>(Command.RETRIEVE, Count.ONE, d));
        if (obj instanceof BaseDTO) {
            return Optional.of((D) obj);
        }
        return Optional.empty();
    }

    @Override
    public List<D> findMany(D d) {
        Object obj = cm.sendRequestAndGetAnswer(new MAMP<>(Command.RETRIEVE, Count.MANY, d));
        if (obj instanceof List) {
            return (List<D>) obj;
        }
        return new ArrayList<>();
    }

    @Override
    public List<D> findAll(D d) {
        Object obj = cm.sendRequestAndGetAnswer(new MAMP<>(Command.RETRIEVE, Count.ALL, d));
        if (obj instanceof List) {
            return (List<D>) obj;
        }
        return new ArrayList<>();
    }
}
