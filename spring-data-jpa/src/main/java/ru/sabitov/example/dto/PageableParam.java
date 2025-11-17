package ru.sabitov.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PageableParam {
    private int page;
    private int size;
    @NotBlank
    private String sort;
}
