package com.fabrica.exception;

public class HeladoAlreadyExistsException extends RuntimeException {
    public HeladoAlreadyExistsException(String message) {
        super(message);
    }
}