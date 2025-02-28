package com.fabrica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Carritos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    @EmbeddedId
    private CarritoPK carritoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClienteId", referencedColumnName = "Clienteid", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "Fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "Estado", nullable = false)
    private String estado;


}