package com.example.vrsoftware.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import tools.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitConfig {

    public static final String FILA_ENTRADA = "fila.notificacao.entrada.wellington";
    public static final String FILA_STATUS = "fila.notificacao.status.wellington";

    @Bean
    public Queue filaEntrada() {
        return new Queue(FILA_ENTRADA, true);
    }

    @Bean
    public Queue filaStatus() {
        return new Queue(FILA_STATUS, true);
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new JacksonJsonMessageConverter((JsonMapper) objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
