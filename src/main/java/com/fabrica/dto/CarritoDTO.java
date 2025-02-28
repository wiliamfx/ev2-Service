package com.fabrica.dto;

import com.fabrica.model.CarritoPK; // Asegúrate de importar CarritoPK
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    private CarritoPK carritoId; 
    private String estado;      
}