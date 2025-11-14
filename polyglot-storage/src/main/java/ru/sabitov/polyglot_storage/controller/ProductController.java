package ru.sabitov.polyglot_storage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.polyglot_storage.dto.CreateProductDto;
import ru.sabitov.polyglot_storage.dto.ProductDto;
import ru.sabitov.polyglot_storage.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody CreateProductDto dto) {
        return productService.create(dto);
    }

    @GetMapping
    public List<ProductDto> findByNameAndCategory(@RequestParam String searchText, @RequestParam Long categoryId) {
        return productService.findByNameAndCategoryId(searchText, categoryId);
    }
}
