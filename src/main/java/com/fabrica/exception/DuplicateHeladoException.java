package com.fabrica.exception;

public class DuplicateHeladoException extends RuntimeException {
    public DuplicateHeladoException(String message) {
        super(message);
    }
}