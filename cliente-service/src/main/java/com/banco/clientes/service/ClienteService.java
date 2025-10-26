package com.banco.clientes.service;

import com.banco.clientes.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerCliente(Long id);
    List<Cliente> listarClientes();
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
