package ru.sabitov.example.dto;

import java.time.Instant;

public record BookCreatedEvent(
        String title,
        String author,
        Instant timestamp
) {
}
