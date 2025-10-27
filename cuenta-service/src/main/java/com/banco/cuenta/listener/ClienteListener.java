package com.banco.cuenta.listener;

import com.banco.cuenta.config.RabbitConfig;
import com.banco.cuenta.exception.CustomException;
import com.banco.cuenta.model.Cuenta;
import com.banco.cuenta.model.dto.ClienteDTO;
import com.banco.cuenta.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClienteListener {

    @Autowired
    private CuentaService cuentaService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void procesarCliente(String mensajeJson) {
        log.info("Mensaje JSON recibido: {}", mensajeJson);

        try {
            ClienteDTO cliente = objectMapper.readValue(
                    objectMapper.readValue(mensajeJson, String.class),
                    ClienteDTO.class);
            if ("CREAR".equalsIgnoreCase(cliente.getEvento())) {
                crearCuenta(cliente);
            } else if ("ACTUALIZADO".equalsIgnoreCase(cliente.getEvento())) {
                actualizarCuenta(cliente);
            } else {
                log.warn("Evento desconocido: {}", cliente.getEvento());
            }

        } catch (Exception e) {
            log.error("Error procesando JSON del cliente", e);
        }
    }

    private void crearCuenta(ClienteDTO cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("AC-" + System.currentTimeMillis());
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(0.0);
        cuenta.setEstado(true);
        cuenta.setClienteNombre(cliente.getNombre());
        cuenta.setIdCliente(cliente.getId());
        cuentaService.crearCuenta(cuenta);
        log.info("Cuenta creada para nuevo cliente: {}", cliente.getNombre());
    }

    private void actualizarCuenta(ClienteDTO cliente) {
        Cuenta cuenta = cuentaService.findById(cliente.getId());
        if (cuenta != null) {
            cuenta.setClienteNombre(cliente.getNombre());
            cuenta.setEstado(true);
            cuentaService.actualizarCuenta(cuenta.getNumeroCuenta(), cuenta);
            log.info("Cuenta actualizada para cliente: {}", cliente.getNombre());
        } else {
            throw new CustomException("No se encontró cuenta para el cliente con id: " + cliente.getId(),
                    HttpStatus.NOT_FOUND);
        }
    }

    private void eliminarCuenta(ClienteDTO cliente) {
        Cuenta cuenta = cuentaService.findById(cliente.getId());
        if (cuenta != null) {
            cuenta.setEstado(false);
            cuentaService.actualizarCuenta(cuenta.getNumeroCuenta(), cuenta);
            log.info("Cuenta desactivada para cliente: {}", cliente.getNombre());
        } else {
            throw new CustomException("No se encontró cuenta para el cliente con id: " + cliente.getId(),
                    HttpStatus.NOT_FOUND);
        }
    }
}
