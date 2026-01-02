package com.example.vrsoftware.controller;

import com.example.vrsoftware.service.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificacao/status")
public class NotificationStatusController {

    private final StatusService statusService;

    public NotificationStatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{mensagemId}")
    public String consultarStatus(@PathVariable String mensagemId) {
        return statusService.buscar(mensagemId);
    }
}
