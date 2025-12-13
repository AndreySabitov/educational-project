package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxEventScheduler {
    private final OutboxEventService outboxEventService;

    @Scheduled(fixedRate = 5000)
    public void sendMessages() {
        outboxEventService.getAndSendNotPublishedEvents();
    }
}
