package ru.sabitov.polyglot_storage.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@AllArgsConstructor
@Getter
public class ProductReview {
    @Id
    private String id;
    private Long productId;
    private int rating;
    private String comment;
    private String author;
}
