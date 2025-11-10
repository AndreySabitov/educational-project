package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBookByAuthor(String author);

    Optional<Book> findBookByTitleAndAuthor(String title, String author);
}