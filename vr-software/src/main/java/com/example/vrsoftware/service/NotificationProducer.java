package com.example.vrsoftware.service;

import com.example.vrsoftware.config.RabbitConfig;
import com.example.vrsoftware.dto.NotificationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;
    private final StatusService statusService;

    public NotificationProducer(RabbitTemplate rabbitTemplate, StatusService statusService) {
        this.rabbitTemplate = rabbitTemplate;
        this.statusService = statusService;
    }

    public void enviar(NotificationRequest request) {
        statusService.salvar(request.mensagemId(), "AGUARDANDO PROCESSAMENTO");

        rabbitTemplate.convertAndSend(
                RabbitConfig.FILA_ENTRADA,
                request
        );
    }
}
