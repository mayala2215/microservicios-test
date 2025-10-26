package com.banco.clientes;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.banco.clientes.model.Cliente;
import com.banco.clientes.service.ClienteService;

@SpringBootTest
public class ClienteServiceTest {

	@Autowired
	private ClienteService clienteService;

	@Test
	void testCrearCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Prueba");
		cliente.setGenero("Masculino");
		cliente.setEdad(28);
		cliente.setIdentificacion("123");
		cliente.setContrasena("abc");
		cliente.setEstado(true);
		Cliente result = clienteService.crearCliente(cliente);
		assertNotNull(result.getId());
	}
}
