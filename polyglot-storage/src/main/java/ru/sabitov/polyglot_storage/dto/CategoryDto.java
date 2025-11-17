package ru.sabitov.polyglot_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private Long id;
    private String name;
}
