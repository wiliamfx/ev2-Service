package com.fabrica.exception;

public class HeladoCannotBeDeletedException extends RuntimeException {
    public HeladoCannotBeDeletedException(String message) {
        super(message);
    }
}