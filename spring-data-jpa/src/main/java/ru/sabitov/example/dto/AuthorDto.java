package ru.sabitov.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private List<String> bookTitles;
}