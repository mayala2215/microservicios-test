package com.banco.cuenta.listener;

import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.dto.ClienteDTO;
import com.banco.cuenta.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ClienteListenerTest {

    @Mock
    private CuentaService cuentaService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClienteListener clienteListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcesarClienteCrearCuenta() throws Exception {
        String innerJson = "{\"evento\":\"CREAR\",\"id\":1,\"nombre\":\"Moises Ayala\",\"estado\":true}";
        String mensajeJson = "\"" + innerJson.replace("\"", "\\\"") + "\"";

        ObjectMapper mapper = new ObjectMapper();
        ClienteDTO cliente = mapper.readValue(innerJson, ClienteDTO.class);

        when(objectMapper.readValue(mensajeJson, String.class)).thenReturn(innerJson);
        when(objectMapper.readValue(innerJson, ClienteDTO.class)).thenReturn(cliente);

        clienteListener.procesarCliente(mensajeJson);

        verify(cuentaService, times(1)).crearCuenta(any(Cuenta.class));
    }

    @Test
    void testProcesarClienteActualizarCuenta() throws Exception {
        String innerJson = "{\"evento\":\"ACTUALIZADO\",\"id\":2,\"nombre\":\"Moises Reyes\",\"estado\":true}";
        String mensajeJson = "\"" + innerJson.replace("\"", "\\\"") + "\"";

        ObjectMapper mapper = new ObjectMapper();
        ClienteDTO cliente = mapper.readValue(innerJson, ClienteDTO.class);

        when(objectMapper.readValue(mensajeJson, String.class)).thenReturn(innerJson);
        when(objectMapper.readValue(innerJson, ClienteDTO.class)).thenReturn(cliente);

        Cuenta cuenta = new Cuenta();
        cuenta.setIdCliente(cliente.getId());
        cuenta.setNumeroCuenta("AC-123");
        when(cuentaService.findById(cliente.getId())).thenReturn(cuenta);

        clienteListener.procesarCliente(mensajeJson);

        verify(cuentaService, times(1)).actualizarCuenta(eq("AC-123"), any(Cuenta.class));
    }
}
