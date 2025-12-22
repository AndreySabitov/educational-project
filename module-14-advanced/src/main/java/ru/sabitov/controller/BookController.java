package ru.sabitov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.dto.BookDto;
import ru.sabitov.dto.CreateBookDto;
import ru.sabitov.service.BookService;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public BookDto create(@RequestBody CreateBookDto dto) {
        return bookService.createBook(dto);
    }

    @GetMapping("/{bookId}")
    public BookDto getById(@PathVariable Long bookId) {
        return bookService.getById(bookId);
    }
}
