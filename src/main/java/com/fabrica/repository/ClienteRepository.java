package com.fabrica.repository;

import com.fabrica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByTelefono(String telefono);
}
