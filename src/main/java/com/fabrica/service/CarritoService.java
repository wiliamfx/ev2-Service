package com.fabrica.service;

import com.fabrica.dto.CarritoDTO;
import com.fabrica.exception.ClienteNotFoundException;
import com.fabrica.exception.CarritoNotFoundException;
import com.fabrica.model.Cliente;
import com.fabrica.model.Carrito;
import com.fabrica.model.CarritoPK;
import com.fabrica.repository.ClienteRepository;
import com.fabrica.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

  
    public Carrito crearCarrito(CarritoDTO carritoDTO) {
       
        Optional<Cliente> cliente = clienteRepository.findById(carritoDTO.getCarritoId().getClienteId());
        if (!cliente.isPresent()) {
            throw new ClienteNotFoundException("El cliente con ID " + carritoDTO.getCarritoId().getClienteId() + " no existe.");
        }

     
        Carrito carrito = new Carrito();
        CarritoPK carritoPK = carritoDTO.getCarritoId();

        carrito.setCarritoId(carritoPK);
        carrito.setFechaCreacion(new Date()); 
        carrito.setEstado(carritoDTO.getEstado());

     
        carrito.setCliente(cliente.get()); 

        return carritoRepository.save(carrito);
    }


    public Carrito obtenerCarrito(CarritoPK carritoPK) {
        return carritoRepository.findById(carritoPK)
                .orElseThrow(() -> new CarritoNotFoundException("Carrito no encontrado con ID " + carritoPK));
    }

    public List<Carrito> obtenerTodosLosCarritos() {
        return carritoRepository.findAll();
    }


    public Carrito actualizarCarrito(CarritoPK carritoPK, CarritoDTO carritoDTO) {
        Carrito carrito = obtenerCarrito(carritoPK); 


        carrito.setEstado(carritoDTO.getEstado());
       

        return carritoRepository.save(carrito);
    }

 
    public void eliminarCarrito(CarritoPK carritoPK) {
        Carrito carrito = obtenerCarrito(carritoPK); 
        carritoRepository.delete(carrito);
    }
}