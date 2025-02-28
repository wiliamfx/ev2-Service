package com.fabrica.service;

import com.fabrica.dto.PedidoDTO;
import com.fabrica.model.Pedido;
import com.fabrica.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setFecha(new Date()); // Establecer la fecha actual
        pedido.setTotal(pedidoDTO.getTotal());
        Pedido savedPedido = pedidoRepository.save(pedido);
        return convertToDTO(savedPedido);
    }

    public PedidoDTO getPedidoById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(this::convertToDTO).orElse(null);
    }

    public List<PedidoDTO> getAllPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(this::convertToDTO).toList();
    }

    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoDTO updatePedido(Long id, PedidoDTO pedidoDTO) {
        Optional<Pedido> existingPedido = pedidoRepository.findById(id);
        if (existingPedido.isPresent()) {
            Pedido pedido = existingPedido.get();
            pedido.setCliente(pedidoDTO.getCliente());
            pedido.setTotal(pedidoDTO.getTotal());
            Pedido updatedPedido = pedidoRepository.save(pedido);
            return convertToDTO(updatedPedido);
        }
        return null;
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        return new PedidoDTO(pedido.getPedidoId(), pedido.getCliente(), pedido.getFecha(), pedido.getTotal());
    }
}