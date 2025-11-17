package ru.sabitov.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(CreateBookDto dto);

    BookDto findById(Long id);

    Page<BookDto> findAll(Pageable pageable);

    void deleteById(Long id);

    Page<BookDto> findByAuthor(Pageable pageable, String author);

    BookDto findByTitleAndAuthor(String title, String author);

    List<BookDto> searchByTitle(String text);

    BookDto createBookWithAuthor(CreateBookDto dto);
}
