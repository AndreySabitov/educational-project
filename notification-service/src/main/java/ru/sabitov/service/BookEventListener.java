package ru.sabitov.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sabitov.NotificationProcessor;
import ru.sabitov.example.dto.BookCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookEventListener {
    private final NotificationProcessor notificationProcessor;

    @KafkaListener(topics = "book_events", containerFactory = "bookEventKafkaListenerContainerFactory")
    public void consumeBookCreatedEvent(BookCreatedEvent bookCreatedEvent) {
        log.info("Приняли уведомление из kafka: {}", bookCreatedEvent);
        notificationProcessor.sendToMongo(bookCreatedEvent);
    }
}
