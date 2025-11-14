package ru.sabitov.polyglot_storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sabitov.polyglot_storage.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p join fetch p.category where lower(p.name) like lower(concat('%', :searchText, '%')) and p.category.id = :categoryId")
    List<Product> findByNameAndCategory(String searchText, Long categoryId);

}