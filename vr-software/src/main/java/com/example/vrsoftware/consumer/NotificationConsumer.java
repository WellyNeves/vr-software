package com.example.vrsoftware.consumer;

import com.example.vrsoftware.config.RabbitConfig;
import com.example.vrsoftware.dto.NotificationRequest;
import com.example.vrsoftware.dto.NotificationStatus;
import com.example.vrsoftware.service.StatusService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final StatusService statusService;

    public NotificationConsumer(RabbitTemplate rabbitTemplate, StatusService statusService) {
        this.rabbitTemplate = rabbitTemplate;
        this.statusService = statusService;
    }

    @RabbitListener(queues = RabbitConfig.FILA_ENTRADA)
    public void processar(NotificationRequest request) throws InterruptedException {

        try {
            Thread.sleep(2000);

            if (Math.random() < 0.2) {
                throw new RuntimeException("Falha simulada no processamento");
            }

            publicarStatus(
                    request.mensagemId(),
                    "PROCESSADO_SUCESSO"
            );

        } catch (Exception e) {
            publicarStatus(
                    request.mensagemId(),
                    "FALHA_PROCESSAMENTO"
            );
        }
    }

    private void publicarStatus(String mensagemId, String status) {
        statusService.salvar(mensagemId, status);

        rabbitTemplate.convertAndSend(
                RabbitConfig.FILA_STATUS,
                new NotificationStatus(mensagemId, status)
        );
    }
}
