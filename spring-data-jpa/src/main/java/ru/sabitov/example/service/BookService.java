package ru.sabitov.example.service;

import org.springframework.data.domain.Page;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.dto.PageableParam;

import java.util.List;

public interface BookService {
    BookDto addBook(CreateBookDto dto);

    BookDto findById(Long id);

    Page<BookDto> findAll(PageableParam pageableParam);

    void deleteById(Long id);

    Page<BookDto> findByAuthor(PageableParam pageableParam, String author);

    BookDto findByTitleAndAuthor(String title, String author);

    List<BookDto> searchByTitle(String text);

    BookDto createBookWithAuthor(CreateBookDto dto);
}
