package ru.sabitov.polyglot_storage.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.polyglot_storage.dto.CreateProductReviewDto;
import ru.sabitov.polyglot_storage.dto.ProductReviewDto;
import ru.sabitov.polyglot_storage.model.ProductReview;

@UtilityClass
public class ProductReviewMapper {

    public ProductReview toEntity(CreateProductReviewDto dto, Long productId) {
        return new ProductReview(null, productId, dto.getRating(), dto.getComment(), dto.getAuthor());
    }

    public ProductReviewDto toDto(ProductReview review) {
        return new ProductReviewDto(review.getId(), review.getProductId(), review.getRating(), review.getComment(),
                review.getAuthor());
    }
}
