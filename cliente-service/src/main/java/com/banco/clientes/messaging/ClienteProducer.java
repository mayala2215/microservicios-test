package com.banco.clientes.messaging;

import com.banco.clientes.config.RabbitConfig;
import com.banco.clientes.model.Cliente;
import com.banco.clientes.model.dto.ClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClienteProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void enviarCliente(Cliente cliente) {
        try {
            ClienteDTO clienteDTO = new ClienteDTO("CREAR", cliente.getId(), cliente.getNombre(), cliente.getEstado());
            String json = objectMapper.writeValueAsString(clienteDTO);
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY,
                    json);

            log.info("JSON enviado a RabbitMQ: {}", json);
        } catch (Exception e) {
            log.error("Error convirtiendo cliente a JSON", e);
        }
    }

    public void enviarClienteActualizado(Cliente cliente) {

        try {
            ClienteDTO clienteDTO = new ClienteDTO("ACTUALIZADO", cliente.getId(), cliente.getNombre(),
                    cliente.getEstado());
            String json = objectMapper.writeValueAsString(clienteDTO);
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY,
                    json);
            log.info("JSON de actualización enviado: {}", json);
        } catch (Exception e) {
            log.error("Error enviando actualización de cliente", e);
        }
    }

    public void enviarClienteEliminado(Cliente cliente) {
        try {
            ClienteDTO clienteDTO = new ClienteDTO("ELIMINADO", cliente.getId(), cliente.getNombre(),
                    cliente.getEstado());
            String json = objectMapper.writeValueAsString(clienteDTO);
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY,
                    json);
            log.info("JSON de eliminación enviado: {}", json);
        } catch (Exception e) {
            log.error("Error enviando eliminación de cliente", e);
        }
    }
}
