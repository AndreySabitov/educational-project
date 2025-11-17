package ru.sabitov.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.service.RestTemplateDemoService;

@RestController
@RequiredArgsConstructor
public class RestTemplateDemoController {
    private final RestTemplateDemoService service;

    @GetMapping("/rest-query")
    public String getQuery() {
        return service.doQuery();
    }
}
