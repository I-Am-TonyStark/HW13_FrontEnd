package com.mamalimomen.base.controllers.utilities;

public final class InValidDataException extends Exception {

    private static final long serialVersionUID = 4122099134875794959L;

    public InValidDataException(String message) {
        super(message);
    }

    public InValidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}