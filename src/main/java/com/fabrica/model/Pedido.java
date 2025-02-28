package com.fabrica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="Pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PedidoId",nullable = false)
    private Long pedidoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id",referencedColumnName = "Clienteid")
    private Cliente cliente;

    @Column(name="Fecha",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name="Total",nullable = false)
    private Double total;
}
