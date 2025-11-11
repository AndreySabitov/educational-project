package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.error.NotFoundException;
import ru.sabitov.example.mapper.BookMapper;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;
import ru.sabitov.example.repository.AuthorRepository;
import ru.sabitov.example.repository.BookRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public BookDto addBook(CreateBookDto dto) {
        Author author = authorRepository.findAuthorByName(dto.getAuthor()).orElseThrow(() ->
                new NotFoundException("Автор не найден"));


        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(dto, author)));
    }

    public BookDto findById(Long id) {
        return BookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Книга с id = %d не найдена".formatted(id))));
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.getAll();

        books.forEach(book -> log.info("Книга {}, автор {}", book.getTitle(), book.getAuthor().getName()));

        return books.stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> findByAuthor(String author) {
        if (author.isBlank()) {
            throw new IllegalArgumentException("Не указан автор");
        }

        return bookRepository.findBookByAuthor_Name(author).stream()
                .map(BookMapper::toDto)
                .toList();
    }

    public BookDto findByTitleAndAuthor(String title, String author) {
        if (author.isBlank() || title.isBlank()) {
            throw new IllegalArgumentException("Не указан автор или название книги");
        }

        return BookMapper.toDto(bookRepository.findBookByTitleAndAuthor_Name(title, author).orElseThrow(() ->
                new NotFoundException("Не удалось найти книгу %s автора %s".formatted(title, author))));
    }

    public List<BookDto> searchByTitle(String text) {
        if (text.isBlank()) {
            return List.of();
        }

        return bookRepository.findByPartOfTitle(text).stream()
                .map(BookMapper::toDto)
                .toList();
    }
}
