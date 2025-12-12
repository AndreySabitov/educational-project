package ru.sabitov.dto;

import java.time.Instant;

public record BookCreatedEvent(
        String title,
        String author,
        Instant timestamp
) {
}
