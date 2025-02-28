package com.fabrica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeladoDTO {
    private Long heladoId;
    private String nombre;
    private String tipo;
    private Double precio;
    private String descripcion;
}