package com.fabrica.repository;

import com.fabrica.model.Helado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeladoRepository extends JpaRepository<Helado,Long> {
    Optional<Helado> findByNombre(String nombre);

}
