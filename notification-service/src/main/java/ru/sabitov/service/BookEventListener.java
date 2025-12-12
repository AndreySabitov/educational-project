package ru.sabitov.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sabitov.example.dto.BookCreatedEvent;
import ru.sabitov.mapper.NotificationMapper;
import ru.sabitov.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class BookEventListener {
    private final NotificationRepository notificationRepository;

    private static final Logger log = LoggerFactory.getLogger(BookEventListener.class);

    @KafkaListener(topics = "book_events", containerFactory = "bookEventKafkaListenerContainerFactory")
    public void consumeBookCreatedEvent(BookCreatedEvent bookCreatedEvent) {
        log.info("Приняли уведомление из kafka: {}", bookCreatedEvent);
        notificationRepository.save(NotificationMapper.toEntity(bookCreatedEvent));
        log.info("Записали уведомление в mongo");
    }
}
