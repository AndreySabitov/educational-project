package ru.sabitov.intro_to_spring_framework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sabitov.intro_to_spring_framework.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    @Value("${app.greeting}")
    private String greeting;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String getMessage() {
        return greeting;
    }

    public String getMessageFromRepository() {
        return messageRepository.getMessage();
    }
}
