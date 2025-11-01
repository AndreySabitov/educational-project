package ru.sabitov.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.mapper.BookMapper;
import ru.sabitov.example.model.Book;

@RestController
@Slf4j
public class BookController {

    @GetMapping("/book")
    public Book getBook() {
        return new Book(1L, "Book", "Author");
    }

    @GetMapping("/books/{id}")
    public Book findById(@PathVariable Long id) {
        log.info("Поступил запрос на поиск книги с id = {}", id);

        return new Book(id, "Book", "Author");
    }

    @GetMapping("/books/search")
    public Book findByTitle(@RequestParam(required = false) String title) {
        log.info("Запрос на поиск книги по title = {}", title);

        return new Book(1L, title, "Tolstoy L.N.");
    }

    @PostMapping("/books")
    public Book create(@RequestBody CreateBookDto bookDto) {
        log.info("Запрос на добавление книги {}", bookDto);

        return BookMapper.mapToBook(bookDto);
    }
}
