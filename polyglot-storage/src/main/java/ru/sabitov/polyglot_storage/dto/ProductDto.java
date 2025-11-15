package ru.sabitov.polyglot_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ProductDto implements Serializable {
    private String name;
    private double price;
    private CategoryDto category;
}
