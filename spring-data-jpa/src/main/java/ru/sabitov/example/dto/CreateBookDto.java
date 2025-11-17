package ru.sabitov.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CreateBookDto {
    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    @NotBlank
    @Size(min = 3, max = 100)
    private String author;
    @Min(value = 1900)
    private int publicationYear;
}