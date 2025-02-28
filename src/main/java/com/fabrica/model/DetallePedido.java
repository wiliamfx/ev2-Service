package com.fabrica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DetallesPedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetalleId", nullable = false)
    private Long detalleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id", referencedColumnName = "PedidoId")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "helado_id", referencedColumnName = "Heladoid")
    private Helado helado;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Subtotal", nullable = false)
    private Double subtotal;
}