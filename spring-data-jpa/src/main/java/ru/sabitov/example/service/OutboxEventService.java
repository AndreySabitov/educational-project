package ru.sabitov.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.BookCreatedEvent;
import ru.sabitov.example.error.BookEventDeserializationException;
import ru.sabitov.example.repository.OutboxEventRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxEventService {
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, BookCreatedEvent> kafkaTemplate;

    @Transactional
    public void getAndSendNotPublishedEvents() {
        outboxEventRepository.findAllNotPublishedEvents().forEach(outboxEvent -> {
            try {
                BookCreatedEvent event = objectMapper.readValue(outboxEvent.getPayload(), BookCreatedEvent.class);
                kafkaTemplate.send("book_events", event);
                outboxEvent.setPublished(true);
            } catch (JsonProcessingException e) {
                throw new BookEventDeserializationException("Ошибка десериализации в BookCreatedEvent");
            }
        });
    }
}
