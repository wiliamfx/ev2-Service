package com.fabrica.controller;

import com.fabrica.dto.HeladoDTO;
import com.fabrica.service.HeladoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helados")
public class HeladoController {

    @Autowired
    private HeladoService heladoService;

    @PostMapping
    public ResponseEntity<HeladoDTO> createHelado(@RequestBody HeladoDTO heladoDTO) {
        HeladoDTO createdHelado = heladoService.saveHelado(heladoDTO);
        return new ResponseEntity<>(createdHelado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeladoDTO> getHeladoById(@PathVariable Long id) {
        HeladoDTO heladoDTO = heladoService.getHeladoById(id);
        return new ResponseEntity<>(heladoDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HeladoDTO>> getAllHelados() {
        List<HeladoDTO> helados = heladoService.getAllHelados();
        return new ResponseEntity<>(helados, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeladoDTO> updateHelado(@PathVariable Long id, @RequestBody HeladoDTO heladoDTO) {
        HeladoDTO updatedHelado = heladoService.updateHelado(id, heladoDTO);
        return new ResponseEntity<>(updatedHelado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHelado(@PathVariable Long id) {
        String message = heladoService.deleteHelado(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}