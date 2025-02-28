package com.fabrica.repository;

import com.fabrica.model.Carrito;
import com.fabrica.model.CarritoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, CarritoPK> {
}
