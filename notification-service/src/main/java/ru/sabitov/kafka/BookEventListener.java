package ru.sabitov.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sabitov.dto.BookCreatedEvent;

@Service
public class BookEventListener {

    private static final Logger log = LoggerFactory.getLogger(BookEventListener.class);

    @KafkaListener(topics = "book_events", containerFactory = "bookEventKafkaListenerContainerFactory")
    public void consumeBookCreatedEvent(BookCreatedEvent bookCreatedEvent) {
        log.info("{}", bookCreatedEvent);
    }
}
