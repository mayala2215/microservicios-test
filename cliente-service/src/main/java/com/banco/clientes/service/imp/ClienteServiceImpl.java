package com.banco.clientes.service.imp;

import com.banco.clientes.model.Cliente;
import com.banco.clientes.messaging.ClienteProducer;
import com.banco.clientes.repository.ClienteRepository;
import com.banco.clientes.service.ClienteService;
import org.springframework.stereotype.Service;
import com.banco.clientes.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    private ClienteProducer clienteProducer;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        Cliente nuevo = clienteRepository.save(cliente);
        clienteProducer.enviarCliente(nuevo);
        return nuevo;
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new CustomException("Cliente no encontrado con id " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Cliente existente = obtenerCliente(id);
        existente.setNombre(cliente.getNombre());
        existente.setDireccion(cliente.getDireccion());
        existente.setTelefono(cliente.getTelefono());
        existente.setContrasena(cliente.getContrasena());
        existente.setEstado(cliente.getEstado());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
