package ru.sabitov.example.dto;

import java.time.Year;

public record BookDto(
        Long id,
        String title,
        String author,
        Year publicationYear
) {
}
