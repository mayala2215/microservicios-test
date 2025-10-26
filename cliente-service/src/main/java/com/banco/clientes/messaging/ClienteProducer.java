package com.banco.clientes.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.banco.clientes.config.RabbitConfig;
import com.banco.clientes.model.Cliente;

@Component
public class ClienteProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviarCliente(Cliente cliente) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.QUEUE_NAME,
                "Nuevo cliente creado: " + cliente.getNombre()
        );

        System.out.println("Mensaje enviado a RabbitMQ: " + cliente.getNombre());
    }
}
