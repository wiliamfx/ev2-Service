package com.fabrica.dto;

import com.fabrica.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long pedidoId;
    private Cliente cliente;
    private Date fecha;
    private Double total;
}
