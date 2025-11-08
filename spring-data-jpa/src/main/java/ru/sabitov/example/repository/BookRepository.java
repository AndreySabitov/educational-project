package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.example.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}