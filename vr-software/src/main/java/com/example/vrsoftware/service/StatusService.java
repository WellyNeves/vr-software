package com.example.vrsoftware.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatusService {

    private final Map<String, String> statusMap = new ConcurrentHashMap<>();

    public void salvar(String id, String status) {
        statusMap.put(id, status);
    }

    public String buscar(String id) {
        return statusMap.getOrDefault(id, "DESCONHECIDO");
    }
}
