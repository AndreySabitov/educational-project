package ru.sabitov.polyglot_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductReviewDto {
    private String id;
    private Long productId;
    private int rating;
    private String comment;
    private String author;
}
