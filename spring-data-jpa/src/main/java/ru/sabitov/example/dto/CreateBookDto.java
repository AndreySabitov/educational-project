package ru.sabitov.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Year;

@AllArgsConstructor
@Getter
@ToString
public class CreateBookDto {
    private String title;
    private String author;
    private Year publicationYear;
}