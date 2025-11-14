package ru.sabitov.polyglot_storage.dto;

import lombok.Getter;

@Getter
public class CreateProductDto {
    private String name;
    private Long categoryId;
}
