package com.fabrica.controller;

import com.fabrica.dto.PedidoDTO;
import com.fabrica.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO createdPedido = pedidoService.savePedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedido(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.getPedidoById(id);
        if (pedidoDTO != null) {
            return ResponseEntity.ok(pedidoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        List<PedidoDTO> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.ok("Pedido eliminado exitosamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO updatedPedido = pedidoService.updatePedido(id, pedidoDTO);
        if (updatedPedido != null) {
            return ResponseEntity.ok(updatedPedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}