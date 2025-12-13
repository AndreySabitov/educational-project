package ru.sabitov;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.sabitov.example.dto.BookCreatedEvent;
import ru.sabitov.mapper.NotificationMapper;
import ru.sabitov.repository.NotificationRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessor {
    private final NotificationRepository notificationRepository;
    private final ExecutorService executorService;

    public CompletableFuture<Void> sendToMongo(BookCreatedEvent bookCreatedEvent) {
        return CompletableFuture.runAsync(() -> {
            log.info("Записываем уведомление в Mongo");
            notificationRepository.save(NotificationMapper.toEntity(bookCreatedEvent));
        }, executorService);
    }
}
