package ru.sabitov.example.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.error.NotFoundException;
import ru.sabitov.example.mapper.BookMapper;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;
import ru.sabitov.example.repository.AuthorRepository;
import ru.sabitov.example.repository.BookRepository;

import java.time.Year;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    private BookServiceImpl bookService;

    private final BookDto bookDto = new BookDto(1L, "Book", "Author", Year.of(2020));
    private final Author author = new Author(1L, "Author");
    private final Book book = new Book(1L, "Book", author, Year.of(2020));

    @BeforeEach
    void init() {
        BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
        bookService = new BookServiceImpl(bookRepository, authorRepository, bookMapper);
    }

    @Test
    void testThrowNotFoundException_WhenTryFindNotExistsBook() {
        assertThrows(NotFoundException.class, () -> bookService.findById(1L));
    }

    @Test
    void testCanFindBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto dto = bookService.findById(1L);

        assertEquals(bookDto, dto);
    }
}