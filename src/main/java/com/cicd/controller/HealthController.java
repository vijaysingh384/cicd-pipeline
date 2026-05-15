package com.cicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "CI/CD Pipeline Working";
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is running successfully");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("version", "1.0.0");
        return response;
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> info = new HashMap<>();
        info.put("application", "CI/CD Pipeline Demo");
        info.put("description", "Java Spring Boot with Docker and GitHub Actions");
        info.put("technology", "Spring Boot, Maven, Docker, GitHub Actions");
        return info;
    }
}
