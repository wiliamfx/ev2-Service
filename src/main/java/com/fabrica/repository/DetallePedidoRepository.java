package com.fabrica.repository;

import com.fabrica.model.DetallePedido;
import com.fabrica.model.Helado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
    boolean existsByHelado_HeladoId(Long heladoId);
}
