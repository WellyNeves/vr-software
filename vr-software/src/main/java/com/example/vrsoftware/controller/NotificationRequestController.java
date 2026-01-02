package com.example.vrsoftware.controller;

import com.example.vrsoftware.dto.NotificationRequest;
import com.example.vrsoftware.service.NotificationProducer;
import com.example.vrsoftware.service.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificar")
public class NotificationRequestController {

    private final NotificationProducer producer;
    private final StatusService statusService;

    public NotificationRequestController(NotificationProducer producer, StatusService statusService) {
        this.producer = producer;
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<?> notificar(@RequestBody NotificationRequest request) {
        producer.enviar(request);
        return ResponseEntity.accepted().body(request.mensagemId());
    }
}
