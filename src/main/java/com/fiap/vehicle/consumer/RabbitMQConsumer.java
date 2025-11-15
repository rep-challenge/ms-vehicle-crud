package com.fiap.vehicle.consumer;

import com.fiap.vehicle.consumer.messages.UpdateStatus;
import com.fiap.vehicle.service.interfaces.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQConsumer {

    private final Vehicle service;

    public RabbitMQConsumer(final Vehicle service) {
        this.service = service;
    }

    @RabbitListener(queues = "${rabbitmq.update.queue.name}")
    public void listen(final UpdateStatus message) {
        log.info("Recebida mensagem: {}", message);
        this.service.updateStatus(message.id(), message.status());
        log.info("Status atualizado com sucesso");
    }
}
