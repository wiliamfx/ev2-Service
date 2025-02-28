package com.fabrica.exception;

public class HeladoNotFoundException extends RuntimeException {
    public HeladoNotFoundException(String message) {
        super(message);
    }
}
