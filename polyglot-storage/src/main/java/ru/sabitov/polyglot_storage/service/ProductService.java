package ru.sabitov.polyglot_storage.service;

import ru.sabitov.polyglot_storage.dto.CreateProductDto;
import ru.sabitov.polyglot_storage.dto.ProductDto;
import ru.sabitov.polyglot_storage.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(CreateProductDto dto);

    List<ProductDto> findByNameAndCategoryId(String searchText, Long categoryId);

    ProductDto findProductById(Long id);

    ProductDto update(UpdateProductDto dto, Long id);

    void deleteById(Long id);
}
