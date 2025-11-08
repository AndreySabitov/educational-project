package ru.sabitov.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CreateBookDto {
    private final String title;
    private final String author;
}