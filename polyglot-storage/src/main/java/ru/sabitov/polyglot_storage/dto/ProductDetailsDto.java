package ru.sabitov.polyglot_storage.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductDetailsDto {
    private String name;
    private double price;
    private CategoryDto category;
    private List<ProductReviewDto> reviews;
}
