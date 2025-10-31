package ru.sabitov.intro_to_spring_framework.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sabitov.intro_to_spring_framework.repository.MessageRepository;

@Slf4j
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    @Value("${app.greeting}")
    private String greeting;

    public MessageService(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
    }

    public String getMessage() {
        return greeting;
    }

    public String getMessageFromRepository() {
        return messageRepository.getMessage();
    }

    @PostConstruct
    private void postConstruct() {
        log.info("Hello from postConstruct!");
    }

    @PreDestroy
    private void preDestroy() {
        log.info("Hello from preDestroy!");
    }
}
