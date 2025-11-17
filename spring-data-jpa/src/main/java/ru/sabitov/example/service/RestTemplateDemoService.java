package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateDemoService {
    private final RestTemplate restTemplate;
    @Value("${demo.url}")
    private String demoUrl;

    public String doQuery() {
        return restTemplate.getForObject(demoUrl, String.class);
    }

}
