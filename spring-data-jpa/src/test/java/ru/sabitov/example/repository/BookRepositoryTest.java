package ru.sabitov.example.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookRepositoryTest {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Test
    void testCanGetBookById() {
        Author author = authorRepository.save(new Author(null, "Author"));
        bookRepository.save(new Book(null, "Title", author, Year.of(1995)));

        List<Book> books = bookRepository.findByPartOfTitle("iTl");

        assertEquals(1, books.size());
        Book book = books.getFirst();
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor().getName());
        assertEquals(Year.of(1995), book.getPublicationYear());
    }
}