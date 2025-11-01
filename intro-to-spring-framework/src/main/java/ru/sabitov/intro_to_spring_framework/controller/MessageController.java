package ru.sabitov.intro_to_spring_framework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.intro_to_spring_framework.service.MessageService;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return messageService.getMessage();
    }

    @GetMapping("/message")
    public String getMessage() {
        return messageService.getMessageFromRepository();
    }
}
