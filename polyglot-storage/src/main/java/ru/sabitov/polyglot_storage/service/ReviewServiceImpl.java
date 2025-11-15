package ru.sabitov.polyglot_storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sabitov.polyglot_storage.dto.CreateProductReviewDto;
import ru.sabitov.polyglot_storage.dto.ProductReviewDto;
import ru.sabitov.polyglot_storage.errors.NotFoundException;
import ru.sabitov.polyglot_storage.mapper.ProductReviewMapper;
import ru.sabitov.polyglot_storage.repository.ProductRepository;
import ru.sabitov.polyglot_storage.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;


    @Override
    public ProductReviewDto create(CreateProductReviewDto dto, Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Продукт с id = %d не найден".formatted(productId));
        }

        return ProductReviewMapper.toDto(reviewRepository.save(ProductReviewMapper.toEntity(dto, productId)));
    }

    @Override
    public List<ProductReviewDto> getByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Продукт с id = %d не найден".formatted(productId));
        }

        return reviewRepository.findProductReviewByProductId(productId).stream()
                .map(ProductReviewMapper::toDto)
                .toList();
    }
}
