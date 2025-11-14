package ru.sabitov.polyglot_storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.polyglot_storage.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}