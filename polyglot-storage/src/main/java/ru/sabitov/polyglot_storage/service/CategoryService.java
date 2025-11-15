package ru.sabitov.polyglot_storage.service;

import ru.sabitov.polyglot_storage.dto.CategoryDto;
import ru.sabitov.polyglot_storage.dto.CreateCategoryDto;

public interface CategoryService {
    CategoryDto create(CreateCategoryDto dto);
}
