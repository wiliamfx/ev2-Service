package com.fabrica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clientes", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Clienteid", nullable = false)
    private Long clienteId;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Telefono", nullable = false, unique = true)
    private String telefono;
}