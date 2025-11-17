package ru.sabitov.polyglot_storage.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.polyglot_storage.dto.CategoryDto;
import ru.sabitov.polyglot_storage.dto.CreateCategoryDto;
import ru.sabitov.polyglot_storage.model.Category;

@UtilityClass
public class CategoryMapper {

    public Category toEntity(CreateCategoryDto dto) {
        return new Category(null, dto.getName());
    }

    public CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
