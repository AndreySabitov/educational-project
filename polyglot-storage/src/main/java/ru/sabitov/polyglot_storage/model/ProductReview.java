package ru.sabitov.polyglot_storage.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "reviews")
public class ProductReview {
    private String id;
    private Long productId;
    private int rating;
    private String comment;
    private String author;
}
