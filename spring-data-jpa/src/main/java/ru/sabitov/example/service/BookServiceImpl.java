package ru.sabitov.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.BookCreatedEvent;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.error.BookEventSerializationException;
import ru.sabitov.example.error.DuplicateException;
import ru.sabitov.example.error.NotFoundException;
import ru.sabitov.example.mapper.BookMapper;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;
import ru.sabitov.example.model.OutboxEvent;
import ru.sabitov.example.repository.AuthorRepository;
import ru.sabitov.example.repository.BookRepository;
import ru.sabitov.example.repository.OutboxEventRepository;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public BookDto addBook(CreateBookDto dto) {
        Author author = authorRepository.findAuthorByName(dto.getAuthor()).orElseThrow(() ->
                new NotFoundException("Автор не найден"));

        BookDto createdBook = bookMapper.toDto(bookRepository.save(bookMapper.toEntity(dto, author)));

        try {
            String payload = objectMapper.writeValueAsString(new BookCreatedEvent(dto.getTitle(), dto.getAuthor(),
                    Instant.now()));
            outboxEventRepository.save(new OutboxEvent(null, payload, false, Instant.now()));
        } catch (JsonProcessingException e) {
            throw new BookEventSerializationException("Ошибка сериализации BookCreatedEvent");
        }

        return createdBook;
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Книга с id = %d не найдена".formatted(id))));
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.getAll(pageable);

        books.forEach(book -> log.info("Книга {}, автор {}", book.getTitle(), book.getAuthor().getName()));

        return new PageImpl<>(books.stream()
                .map(bookMapper::toDto)
                .toList(), books.getPageable(), books.getTotalElements());
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Книга с id = %d не найдена".formatted(id));
        }

        bookRepository.deleteById(id);
    }

    @Override
    public Page<BookDto> findByAuthor(Pageable pageable, String author) {
        if (author.isBlank()) {
            throw new IllegalArgumentException("Не указан автор");
        }

        Page<Book> books = bookRepository.findBookByAuthor_Name(author, pageable);

        return new PageImpl<>(books.stream()
                .map(bookMapper::toDto)
                .toList(), books.getPageable(), books.getTotalElements());
    }

    @Override
    public BookDto findByTitleAndAuthor(String title, String author) {
        if (author.isBlank() || title.isBlank()) {
            throw new IllegalArgumentException("Не указан автор или название книги");
        }

        return bookMapper.toDto(bookRepository.findBookByTitleAndAuthor_Name(title, author).orElseThrow(() ->
                new NotFoundException("Не удалось найти книгу %s автора %s".formatted(title, author))));
    }

    @Override
    public List<BookDto> searchByTitle(String text) {
        if (text.isBlank()) {
            return List.of();
        }

        return bookRepository.findByPartOfTitle(text).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public BookDto createBookWithAuthor(CreateBookDto dto) {
        if (authorRepository.existsByName(dto.getAuthor())) {
            throw new DuplicateException("Автор с именем '%s' уже существует".formatted(dto.getAuthor()));
        }
        Author author = authorRepository.save(new Author(null, dto.getAuthor()));

        if (true) {
            throw new RuntimeException();
        }

        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(dto, author)));
    }
}
