package ru.sabitov.polyglot_storage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sabitov.polyglot_storage.model.ProductReview;

public interface ReviewRepository extends MongoRepository<ProductReview, String> {
}