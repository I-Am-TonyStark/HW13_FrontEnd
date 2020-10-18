package com.mamalimomen.base.views.impl;

import com.mamalimomen.base.dtos.BaseDTO;
import com.mamalimomen.base.models.BaseModel;
import com.mamalimomen.base.views.BaseView;

import java.util.List;
import java.util.Optional;

public abstract class BaseViewImpl<D extends BaseDTO, MOD extends BaseModel<D>> implements BaseView<D> {
    protected final MOD model;

    public BaseViewImpl(MOD model) {
        this.model = model;
    }

    @Override
    public void closeClientManger() {
        model.closeClientManger();
    }

    @Override
    public String saveOne(D d) {
        return model.saveOne(d);
    }

    @Override
    public String saveMany(List<D> l) {
        return model.saveMany(l);
    }

    @Override
    public String updateOne(D d) {
        return model.updateOne(d);
    }

    @Override
    public String updateMany(List<D> l) {
        return model.updateMany(l);
    }

    @Override
    public String deleteOne(D d) {
        return model.deleteOne(d);
    }

    @Override
    public String deleteMany(List<D> l) {
        return model.deleteMany(l);
    }

    @Override
    public Optional<D> findOne(D d) {
        return model.findOne(d);
    }

    @Override
    public List<D> findMany(D d) {
        return model.findMany(d);
    }

    @Override
    public List<D> findAll(D d) {
        return model.findAll(d);
    }
}
