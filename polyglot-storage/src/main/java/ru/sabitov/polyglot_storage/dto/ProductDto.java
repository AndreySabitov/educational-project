package ru.sabitov.polyglot_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private CategoryDto category;
}
