package ru.sabitov.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.service.AsyncDemoService;

@RestController
@RequiredArgsConstructor
public class AsyncDemoController {
    private final AsyncDemoService asyncDemoService;

    @GetMapping("/async")
    public void runAsync() {
        asyncDemoService.doTask();
    }
}
