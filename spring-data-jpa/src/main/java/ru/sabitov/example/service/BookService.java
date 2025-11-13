package ru.sabitov.example.service;

import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;

import java.util.List;

public interface BookService {
    BookDto addBook(CreateBookDto dto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    void deleteById(Long id);

    List<BookDto> findByAuthor(String author);

    BookDto findByTitleAndAuthor(String title, String author);

    List<BookDto> searchByTitle(String text);

    BookDto createBookWithAuthor(CreateBookDto dto);
}
