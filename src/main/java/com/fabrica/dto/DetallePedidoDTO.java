package com.fabrica.dto;

import com.fabrica.model.Helado;
import com.fabrica.model.Pedido;
import lombok.Data;

@Data
public class DetallePedidoDTO {
    private Long detalleId;
    private Pedido pedido;
    private Helado helado;
    private Integer cantidad;
    private Double subtotal;
    private Long clienteId;


}