package com.fabrica.controller;

import com.fabrica.dto.DetallePedidoDTO;
import com.fabrica.model.DetallePedido;
import com.fabrica.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle_pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @PostMapping
    public ResponseEntity<DetallePedido> crearDetalle(@RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedido nuevoDetalle = detallePedidoService.agregarDetalle(detallePedidoDTO);
        return ResponseEntity.ok(nuevoDetalle);
    }

    @GetMapping
    public ResponseEntity<List<DetallePedido>> obtenerDetalles() {
        List<DetallePedido> detalles = detallePedidoService.obtenerTodosLosDetalles();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> obtenerDetallePorId(@PathVariable Long id) {
        DetallePedido detalle = detallePedidoService.obtenerDetallePorId(id);
        return ResponseEntity.ok(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetalle(@PathVariable Long id, @RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detalleActualizado = detallePedidoService.actualizarDetalle(id, detallePedidoDTO);
        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detallePedidoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }


}