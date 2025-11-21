package ru.sabitov.example.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sabitov.example.error.NotFoundException;
import ru.sabitov.example.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void testThrowNotFoundException_WhenTryFindNotExistsBook() {
        assertThrows(NotFoundException.class, () -> bookService.findById(1L));
    }
}