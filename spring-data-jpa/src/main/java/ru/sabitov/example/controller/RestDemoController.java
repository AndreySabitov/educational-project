package ru.sabitov.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.sabitov.example.service.RestDemoService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestDemoController {
    private final RestDemoService service;

    @GetMapping("/rest-template-demo")
    public String getQueryWithRestTemplate() {
        log.info("Обращаемся к публичному API с помощью RestTemplate");
        return service.doQueryWithRestTemplate();
    }

    @GetMapping("/web-client-demo")
    public Mono<String> getQueryWithWebClient() {
        log.info("Обращаемся к публичному API с помощью WebClient");
        return service.doQueryWithWebClient();
    }
}
