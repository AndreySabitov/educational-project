package ru.sabitov.polyglot_storage.service;

import ru.sabitov.polyglot_storage.dto.CreateProductReviewDto;
import ru.sabitov.polyglot_storage.dto.ProductReviewDto;

import java.util.List;

public interface ReviewService {
    ProductReviewDto create(CreateProductReviewDto dto, Long productId);

    List<ProductReviewDto> getByProductId(Long productId);
}
