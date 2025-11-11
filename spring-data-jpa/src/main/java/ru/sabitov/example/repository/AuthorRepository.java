package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.example.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByName(String name);

    boolean existsByName(String name);
}