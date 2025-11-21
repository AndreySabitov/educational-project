package ru.sabitov.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<BookDto> create(@Valid @RequestBody CreateBookDto bookDto) {
        log.info("Запрос на добавление книги {}", bookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        log.info("Поступил запрос на поиск книги с id = {}", id);

        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<BookDto>> findAll(Pageable pageable,
                                                 @RequestParam(required = false) String author) {
        if (author != null) {
            log.info("Запрос на получение всех книг автора {}", author);
            return ResponseEntity.ok(bookService.findByAuthor(pageable, author));
        }

        log.info("Запрос на получение всех книг");
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Поступил запрос на удаление книги с id = {}", id);

        bookService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-title-and-author")
    public ResponseEntity<BookDto> findByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        log.info("Поступил запрос на поиск книги по автору и названию. Title: {}, Author: {}", title, author);

        return ResponseEntity.ok(bookService.findByTitleAndAuthor(title, author));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> searchByTitle(@RequestParam String text) {
        log.info("Поступил запрос на поиск книг по названию");

        return ResponseEntity.ok(bookService.searchByTitle(text));
    }

    @PostMapping("/create-with-author")
    public ResponseEntity<BookDto> createWithAuthor(@RequestBody CreateBookDto dto) {
        log.info("Поступил запрос на добавление новой книги вместе с новым автором. Book: {}, Author: {}",
                dto.getTitle(), dto.getAuthor());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBookWithAuthor(dto));
    }
}
