package ru.sabitov.polyglot_storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.polyglot_storage.dto.CategoryDto;
import ru.sabitov.polyglot_storage.dto.CreateCategoryDto;
import ru.sabitov.polyglot_storage.errors.DuplicateException;
import ru.sabitov.polyglot_storage.mapper.CategoryMapper;
import ru.sabitov.polyglot_storage.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryDto create(CreateCategoryDto dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new DuplicateException("Категория '%s' уже существует".formatted(dto.getName()));
        }

        return CategoryMapper.toDto(categoryRepository.save(CategoryMapper.toEntity(dto)));
    }
}
