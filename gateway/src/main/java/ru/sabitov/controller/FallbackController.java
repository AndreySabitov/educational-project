package ru.sabitov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.sabitov.dto.ErrorResponse;

@RestController
public class FallbackController {

    @GetMapping("/bookServiceFallback")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<ErrorResponse> bookServiceFallback() {
        return Mono.just(new ErrorResponse("Book service is unavailable"));
    }
}
