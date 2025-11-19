package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestDemoService {
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @Value("${demo.url}")
    private String demoUrl;

    public String doQueryWithRestTemplate() {
        return restTemplate.getForObject(demoUrl, String.class);
    }

    public Mono<String> doQueryWithWebClient() {
        return webClient.get()
                .uri(demoUrl)
                .retrieve()
                .bodyToMono(String.class);
    }
}
