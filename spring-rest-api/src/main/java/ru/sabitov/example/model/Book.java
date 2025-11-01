package ru.sabitov.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;
}
