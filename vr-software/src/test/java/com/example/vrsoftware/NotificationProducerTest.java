package com.example.vrsoftware;


import com.example.vrsoftware.config.RabbitConfig;
import com.example.vrsoftware.dto.NotificationRequest;
import com.example.vrsoftware.service.NotificationProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NotificationProducerTest {

    @MockitoBean
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NotificationProducer producer;

    @Test
    void devePublicarMensagemNaFila() {
        NotificationRequest req =
                new NotificationRequest("123456", "Mensagem Teste");

        producer.enviar(req);

        verify(rabbitTemplate)
                .convertAndSend(
                        eq(RabbitConfig.FILA_ENTRADA),
                        eq(req)
                );
    }
}
