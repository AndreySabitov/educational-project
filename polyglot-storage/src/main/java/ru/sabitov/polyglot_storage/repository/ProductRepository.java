package ru.sabitov.polyglot_storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.polyglot_storage.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}