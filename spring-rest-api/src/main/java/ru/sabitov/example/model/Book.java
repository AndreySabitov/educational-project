package ru.sabitov.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
}
