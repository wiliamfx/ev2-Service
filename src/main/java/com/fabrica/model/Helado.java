package com.fabrica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Helados",uniqueConstraints = @UniqueConstraint(columnNames = "Nombre"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Helado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Heladoid",nullable = false)
    private Long heladoId;

    @Column(name="Nombre",nullable = false)
    private String nombre;

    @Column(name="Tipo",nullable = false)
    private String tipo;

    @Column(name="Precio",nullable = false)
    private Double precio;

    @Column(name="Descripcion",nullable = false)
    private String descripcion;
}