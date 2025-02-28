package com.fabrica.exception;

public class CarritoNotFoundException extends RuntimeException {
    public CarritoNotFoundException(String message) {
        super(message);
    }
}