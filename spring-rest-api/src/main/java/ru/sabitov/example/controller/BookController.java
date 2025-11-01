package ru.sabitov.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.model.Book;

@RestController
public class BookController {

    @GetMapping("/book")
    public Book getBook() {
        return new Book("Book", "Author");
    }
}
