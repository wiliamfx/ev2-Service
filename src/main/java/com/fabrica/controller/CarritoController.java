package com.fabrica.controller;

import com.fabrica.dto.CarritoDTO;
import com.fabrica.model.Carrito;
import com.fabrica.model.CarritoPK;
import com.fabrica.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;


    @PostMapping
    public ResponseEntity<Carrito> crearCarrito(@RequestBody CarritoDTO carritoDTO) {
        Carrito nuevoCarrito = carritoService.crearCarrito(carritoDTO);
        return new ResponseEntity<>(nuevoCarrito, HttpStatus.CREATED);
    }


    @GetMapping("/{carritoId}/{clienteId}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long carritoId, @PathVariable Long clienteId) {
        CarritoPK carritoPK = new CarritoPK(carritoId, clienteId);
        Carrito carrito = carritoService.obtenerCarrito(carritoPK);
        return new ResponseEntity<>(carrito, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> obtenerTodosLosCarritos() {
        List<Carrito> carritos = carritoService.obtenerTodosLosCarritos();
        return new ResponseEntity<>(carritos, HttpStatus.OK);
    }

    @PutMapping("/{carritoId}/{clienteId}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable Long carritoId, @PathVariable Long clienteId, @RequestBody CarritoDTO carritoDTO) {
        CarritoPK carritoPK = new CarritoPK(carritoId, clienteId);
        Carrito carritoActualizado = carritoService.actualizarCarrito(carritoPK, carritoDTO);
        return new ResponseEntity<>(carritoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{carritoId}/{clienteId}")
    public ResponseEntity<String> eliminarCarrito(@PathVariable Long carritoId, @PathVariable Long clienteId) {
        CarritoPK carritoPK = new CarritoPK(carritoId, clienteId);
        try {
            carritoService.eliminarCarrito(carritoPK);
            return ResponseEntity.ok("Eliminación correcta");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrito no encontrado");
        }
    }
}