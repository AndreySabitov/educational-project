package ru.sabitov.polyglot_storage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.polyglot_storage.dto.CategoryDto;
import ru.sabitov.polyglot_storage.dto.CreateCategoryDto;
import ru.sabitov.polyglot_storage.service.CategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody CreateCategoryDto dto) {
        return categoryService.create(dto);
    }
}
