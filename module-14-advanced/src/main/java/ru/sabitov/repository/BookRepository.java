package ru.sabitov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}