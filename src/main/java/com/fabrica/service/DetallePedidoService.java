package com.fabrica.service;

import com.fabrica.dto.DetallePedidoDTO;
import com.fabrica.exception.DuplicateHeladoException;
import com.fabrica.model.DetallePedido;
import com.fabrica.model.Pedido;
import com.fabrica.model.Helado;
import com.fabrica.repository.ClienteRepository;
import com.fabrica.repository.DetallePedidoRepository;
import com.fabrica.repository.PedidoRepository;
import com.fabrica.repository.HeladoRepository;
import com.fabrica.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private HeladoRepository heladoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

  
    public DetallePedido agregarDetalle(DetallePedidoDTO detallePedidoDTO) {
   
        if (detallePedidoDTO.getClienteId() == null) {
            throw new IllegalArgumentException("El ID del cliente no debe ser nulo.");
        }

        if (!clienteRepository.existsById(detallePedidoDTO.getClienteId())) {
            throw new ResourceNotFoundException("El cliente con ID " + detallePedidoDTO.getClienteId() + " no existe.");
        }

        
        Helado helado = detallePedidoDTO.getHelado();
        Optional<Helado> heladoExistenteOpt = heladoRepository.findByNombre(helado.getNombre());

        if (heladoExistenteOpt.isPresent()) {
           
            helado = heladoExistenteOpt.get();
        } else {
           
            helado = heladoRepository.save(helado); 
        }

       
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteRepository.findById(detallePedidoDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ID " + detallePedidoDTO.getClienteId() + " no existe.")));
        pedido.setFecha(new Date());
        pedido.setTotal(detallePedidoDTO.getSubtotal()); 
        pedido = pedidoRepository.save(pedido); 

       
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(detallePedidoDTO.getCantidad());
        detallePedido.setSubtotal(detallePedidoDTO.getSubtotal());
        detallePedido.setHelado(helado);
        detallePedido.setPedido(pedido);

       
        return detallePedidoRepository.save(detallePedido);
    }


    public List<DetallePedido> obtenerTodosLosDetalles() {
        return detallePedidoRepository.findAll();
    }


    public DetallePedido obtenerDetallePorId(Long detalleId) {
        return detallePedidoRepository.findById(detalleId)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de pedido no encontrado con ID: " + detalleId));
    }

    public DetallePedido actualizarDetalle(Long detalleId, DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detallePedido = obtenerDetallePorId(detalleId);
        detallePedido.setCantidad(detallePedidoDTO.getCantidad());
        detallePedido.setSubtotal(detallePedidoDTO.getSubtotal());

       
        Pedido pedido = pedidoRepository.findById(detallePedidoDTO.getPedido().getPedidoId())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: " + detallePedidoDTO.getPedido().getPedidoId()));
        detallePedido.setPedido(pedido);

      
        Helado helado = heladoRepository.findById(detallePedidoDTO.getHelado().getHeladoId())
                .orElseThrow(() -> new ResourceNotFoundException("Helado no encontrado con ID: " + detallePedidoDTO.getHelado().getHeladoId()));
        detallePedido.setHelado(helado);

   
        return detallePedidoRepository.save(detallePedido);
    }


    public void eliminarDetalle(Long detalleId) {
        DetallePedido detallePedido = obtenerDetallePorId(detalleId);
        detallePedidoRepository.delete(detallePedido);
    }
}