package com.mamalimomen.base.models;

import com.mamalimomen.base.dtos.BaseDTO;

import java.util.List;
import java.util.Optional;

public interface BaseModel<D extends BaseDTO> {
    void closeClientManger();

    String saveOne(D d);

    String saveMany(List<D> l);

    String updateOne(D d);

    String updateMany(List<D> l);

    String deleteOne(D d);

    String deleteMany(List<D> l);

    Optional<D> findOne(D d);

    List<D> findMany(D d);

    List<D> findAll(D d);
}
