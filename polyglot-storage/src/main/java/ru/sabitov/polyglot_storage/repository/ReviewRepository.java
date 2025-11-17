package ru.sabitov.polyglot_storage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sabitov.polyglot_storage.model.ProductReview;

import java.util.List;

public interface ReviewRepository extends MongoRepository<ProductReview, String> {
    List<ProductReview> findProductReviewByProductId(Long productId);
}