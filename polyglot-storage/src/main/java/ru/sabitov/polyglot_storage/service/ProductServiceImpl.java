package ru.sabitov.polyglot_storage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.polyglot_storage.dto.CreateProductDto;
import ru.sabitov.polyglot_storage.dto.ProductDto;
import ru.sabitov.polyglot_storage.dto.UpdateProductDto;
import ru.sabitov.polyglot_storage.errors.NotFoundException;
import ru.sabitov.polyglot_storage.mapper.ProductMapper;
import ru.sabitov.polyglot_storage.model.Category;
import ru.sabitov.polyglot_storage.model.Product;
import ru.sabitov.polyglot_storage.repository.CategoryRepository;
import ru.sabitov.polyglot_storage.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDto create(CreateProductDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() ->
                new NotFoundException("Категория с id = %d не найдена".formatted(dto.getCategoryId())));

        return ProductMapper.toDto(productRepository.save(ProductMapper.toEntity(dto, category)));
    }

    @Override
    public List<ProductDto> findByNameAndCategoryId(String searchText, Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Категория с id = %d не найдена".formatted(categoryId));
        }

        return productRepository.findByNameAndCategory(searchText, categoryId).stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDto findProductById(Long id) {
        return ProductMapper.toDto(productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукт с id = %d не найден".formatted(id))));
    }

    @Override
    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public ProductDto update(UpdateProductDto dto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукт с id = %d не найден".formatted(id)));
        String name = dto.getName();
        Double price = dto.getPrice();
        if (name != null && !name.isBlank()) {
            product.setName(dto.getName());
        }
        if (price != null) {
            product.setPrice(price);
        }

        return ProductMapper.toDto(product);
    }

    @Override
    @Transactional
    @CacheEvict(value = "products", key = "#id")
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Продукт с id = %d не найден".formatted(id));
        }

        productRepository.deleteById(id);
    }
}
