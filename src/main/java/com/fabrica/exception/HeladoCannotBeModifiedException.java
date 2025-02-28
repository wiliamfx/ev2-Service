package com.fabrica.exception;

public class HeladoCannotBeModifiedException extends RuntimeException {
    public HeladoCannotBeModifiedException(String message) {
        super(message);
    }
}