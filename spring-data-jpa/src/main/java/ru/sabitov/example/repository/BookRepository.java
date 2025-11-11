package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sabitov.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b from Book b join fetch b.author")
    List<Book> getAll();

    List<Book> findBookByAuthor_Name(String author);

    Optional<Book> findBookByTitleAndAuthor_Name(String title, String author);

    @Query("SELECT b from Book b where lower(b.title) like lower(concat('%', :text, '%')) ORDER BY b.publicationYear DESC")
    List<Book> findByPartOfTitle(String text);
}