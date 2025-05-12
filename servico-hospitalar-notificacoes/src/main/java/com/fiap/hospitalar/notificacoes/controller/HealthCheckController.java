package com.fiap.hospitalar.notificacoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "A aplicação está em execução!";
    }
}
//todo: revisar