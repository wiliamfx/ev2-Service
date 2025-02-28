package com.fabrica.exception;

public class DetallePedidoCannotBeDeletedException extends RuntimeException {
    public DetallePedidoCannotBeDeletedException(String message) {
        super(message);
    }
}