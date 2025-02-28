package com.fabrica.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarritoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long carritoId; 
    private Long clienteId; 


    public CarritoPK() {}


    public CarritoPK(Long carritoId, Long clienteId) {
        this.carritoId = carritoId;
        this.clienteId = clienteId;
    }

    // Getters y Setters
    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarritoPK)) return false;
        CarritoPK that = (CarritoPK) o;
        return Objects.equals(carritoId, that.carritoId) && Objects.equals(clienteId, that.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carritoId, clienteId);
    }
}