package com.fabrica.service;

import com.fabrica.dto.HeladoDTO;
import com.fabrica.exception.HeladoAlreadyExistsException;
import com.fabrica.exception.HeladoNotFoundException;
import com.fabrica.model.Helado;
import com.fabrica.repository.HeladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeladoService {

    @Autowired
    private HeladoRepository heladoRepository;

    public HeladoDTO saveHelado(HeladoDTO heladoDTO) {
       
        if (heladoRepository.findByNombre(heladoDTO.getNombre()).isPresent()) {
            throw new HeladoAlreadyExistsException("El helado con nombre " + heladoDTO.getNombre() + " ya está registrado.");
        }

        Helado helado = new Helado();
        helado.setNombre(heladoDTO.getNombre());
        helado.setTipo(heladoDTO.getTipo());
        helado.setPrecio(heladoDTO.getPrecio());
        helado.setDescripcion(heladoDTO.getDescripcion());

        Helado savedHelado = heladoRepository.save(helado);
        return convertToDTO(savedHelado);
    }

    @Cacheable(value = "helados", key = "#id")
    public HeladoDTO getHeladoById(Long id) {
        Optional<Helado> helado = heladoRepository.findById(id);
        return helado.map(this::convertToDTO)
                .orElseThrow(() -> new HeladoNotFoundException("Helado no encontrado con ID: " + id));
    }

    @Cacheable(value = "helados")
    public List<HeladoDTO> getAllHelados() {
        List<Helado> helados = heladoRepository.findAll();
        return helados.stream().map(this::convertToDTO).toList();
    }

    @CacheEvict(value = "helados", key = "#id")
    public String deleteHelado(Long id) {
        if (!heladoRepository.existsById(id)) {
            throw new HeladoNotFoundException("Helado no encontrado con ID: " + id);
        }

        heladoRepository.deleteById(id);
        return "Helado eliminado exitosamente con ID: " + id;
    }

    @CachePut(value = "helados", key = "#id")
    public HeladoDTO updateHelado(Long id, HeladoDTO heladoDTO) {
        Optional<Helado> existingHelado = heladoRepository.findById(id);
        if (existingHelado.isPresent()) {
            Helado helado = existingHelado.get();
            helado.setNombre(heladoDTO.getNombre());
            helado.setTipo(heladoDTO.getTipo());
            helado.setPrecio(heladoDTO.getPrecio());
            helado.setDescripcion(heladoDTO.getDescripcion());

            Helado updatedHelado = heladoRepository.save(helado);
            return convertToDTO(updatedHelado);
        }
        throw new HeladoNotFoundException("Helado no encontrado con ID: " + id);
    }

    private HeladoDTO convertToDTO(Helado helado) {
        return new HeladoDTO(helado.getHeladoId(), helado.getNombre(), helado.getTipo(), helado.getPrecio(), helado.getDescripcion());
    }
}