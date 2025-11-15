package ru.sabitov.polyglot_storage.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.polyglot_storage.dto.CreateProductDto;
import ru.sabitov.polyglot_storage.dto.ProductDto;
import ru.sabitov.polyglot_storage.model.Category;
import ru.sabitov.polyglot_storage.model.Product;

@UtilityClass
public class ProductMapper {

    public Product toEntity(CreateProductDto dto, Category category) {
        return new Product(null, dto.getName(), dto.getPrice(), category);
    }

    public ProductDto toDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice(), CategoryMapper.toDto(product.getCategory()));
    }
}
