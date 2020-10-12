package com.mamalimomen.base.dtos;

import java.io.Serializable;

public abstract class BaseDTO<PK extends Number> implements Serializable {

    private static final long serialVersionUID = 1131263775501712167L;

    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
