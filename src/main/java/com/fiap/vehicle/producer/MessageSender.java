package com.fiap.vehicle.producer;

import com.fiap.vehicle.model.VehicleModel;
import com.fiap.vehicle.producer.interfaces.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender implements Sender {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public MessageSender(final RabbitTemplate rabbitTemplate, final Queue queue) {
        template = rabbitTemplate;
        this.queue = queue;
    }

    public void send(final VehicleModel vehicle) {
        try {
            this.template.convertAndSend(queue.getName(), vehicle);
            log.info("✅ Dado enviado para a fila com sucesso");
        } catch (Exception e) {
            log.error("❌ Falha ao enviar mensagem para a fila", e);
        }
    }
}
