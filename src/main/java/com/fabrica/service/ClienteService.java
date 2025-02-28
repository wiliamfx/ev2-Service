package com.fabrica.service;

import com.fabrica.dto.ClienteDTO;
import com.fabrica.model.Cliente;
import com.fabrica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
        
        Optional<Cliente> existingEmailCliente = clienteRepository.findByEmail(clienteDTO.getEmail());
        if (existingEmailCliente.isPresent()) {
            throw new RuntimeException("El cliente ya está registrado con este email: " + clienteDTO.getEmail());
        }

       
        Optional<Cliente> existingTelefonoCliente = clienteRepository.findByTelefono(clienteDTO.getTelefono());
        if (existingTelefonoCliente.isPresent()) {
            throw new RuntimeException("El cliente ya está registrado con este teléfono: " + clienteDTO.getTelefono());
        }

        
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        Cliente savedCliente = clienteRepository.save(cliente);
        clienteDTO.setClienteId(savedCliente.getClienteId());

        return clienteDTO;
    }

    @Cacheable(value = "clientes", key = "#id")
    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());

        System.out.println("Recuperando cliente de la base de datos... ID: " + id);
        return dto;
    }

    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(cliente -> {
                    ClienteDTO dto = new ClienteDTO();
                    dto.setClienteId(cliente.getClienteId());
                    dto.setNombre(cliente.getNombre());
                    dto.setEmail(cliente.getEmail());
                    dto.setTelefono(cliente.getTelefono());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "clientes", key = "#id")
    public void deleteCliente(Long id) {
        System.out.println("Eliminando el cliente con el ID: " + id);
        clienteRepository.deleteById(id);
    }

    @CachePut(value = "clientes", key = "#clienteDTO.clienteId")
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        Cliente updatedCliente = clienteRepository.save(cliente);
        clienteDTO.setClienteId(updatedCliente.getClienteId());

        return clienteDTO;
    }
}