package ru.sabitov.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.example.dto.BookCreatedEvent;
import ru.sabitov.model.Notification;

@UtilityClass
public class NotificationMapper {

    public Notification toEntity(BookCreatedEvent event) {
        return new Notification(null, event.title(), event.author(), event.timestamp());
    }
}
