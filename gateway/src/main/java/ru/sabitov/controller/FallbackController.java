package ru.sabitov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.sabitov.dto.ErrorResponse;

@RestController
public class FallbackController {

    @RequestMapping("/bookServiceFallback")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<ErrorResponse> bookServiceFallback() {
        return Mono.just(new ErrorResponse("Book service is unavailable"));
    }
}
