package ru.sabitov.service;

import ru.sabitov.dto.BookDto;
import ru.sabitov.dto.CreateBookDto;

public interface BookService {

    BookDto createBook(CreateBookDto dto);

    BookDto getById(Long bookId);

}
