package ru.sabitov.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.model.Book;

@RestController
@Slf4j
public class BookController {

    @GetMapping("/book")
    public Book getBook() {
        return new Book(1L,"Book", "Author");
    }

    @GetMapping("/books/{id}")
    public Book findById(@PathVariable Long id) {
        log.info("Поступил запрос на поиск книги с id = {}", id);

        return new Book(id,"Book", "Author");
    }
}
