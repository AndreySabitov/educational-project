package ru.sabitov.polyglot_storage.dto;

import lombok.Getter;

@Getter
public class CreateProductReviewDto {
    private int rating;
    private String comment;
    private String author;
}
