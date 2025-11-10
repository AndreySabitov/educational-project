package ru.sabitov.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody CreateBookDto bookDto) {
        log.info("Запрос на добавление книги {}", bookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        log.info("Поступил запрос на поиск книги с id = {}", id);

        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll(@RequestParam(required = false) String author) {
        log.info("Запрос на получение всех книг");

        if (author != null) {
            return ResponseEntity.ok(bookService.findByAuthor(author));
        }

        return ResponseEntity.ok(bookService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Поступил запрос на удаление книги с id = {}", id);

        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-title-and-author")
    public ResponseEntity<BookDto> findByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        return ResponseEntity.ok(bookService.findByTitleAndAuthor(title, author));
    }
}
