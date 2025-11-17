package ru.sabitov.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PageableParam {
    private int page;
    private int size;
    private String sort;
}
