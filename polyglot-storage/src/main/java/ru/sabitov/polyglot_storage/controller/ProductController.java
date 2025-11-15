package ru.sabitov.polyglot_storage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.polyglot_storage.dto.*;
import ru.sabitov.polyglot_storage.service.ProductService;
import ru.sabitov.polyglot_storage.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody CreateProductDto dto) {
        return productService.create(dto);
    }

    @GetMapping
    public List<ProductDto> findByNameAndCategory(@RequestParam String searchText, @RequestParam Long categoryId) {
        return productService.findByNameAndCategoryId(searchText, categoryId);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PatchMapping("/{id}")
    public ProductDto update(@RequestBody UpdateProductDto dto, @PathVariable Long id) {
        return productService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/{productId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductReviewDto addReview(@RequestBody CreateProductReviewDto dto, @PathVariable Long productId) {
        return reviewService.create(dto, productId);
    }

    @GetMapping("/{productId}/reviews")
    public List<ProductReviewDto> getReviewsByProduct(@PathVariable Long productId) {
        return reviewService.getByProductId(productId);
    }
}
