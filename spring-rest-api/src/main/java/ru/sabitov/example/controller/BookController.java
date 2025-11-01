package ru.sabitov.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.dto.UpdateBookDto;
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
    public ResponseEntity<Book> create(@RequestBody CreateBookDto bookDto) {
        log.info("Запрос на добавление книги {}", bookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.mapToBook(bookDto));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@RequestBody UpdateBookDto updateDto, @PathVariable Long id) {
        log.info("Запрос на обновление книги с id = {}", id);

        return ResponseEntity.ok(new Book(id, updateDto.getTitle(), updateDto.getAuthor()));
    }
}
