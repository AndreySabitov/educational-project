package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.dto.PageableParam;
import ru.sabitov.example.error.DuplicateException;
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
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public BookDto addBook(CreateBookDto dto) {
        Author author = authorRepository.findAuthorByName(dto.getAuthor()).orElseThrow(() ->
                new NotFoundException("Автор не найден"));


        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(dto, author)));
    }

    @Override
    public BookDto findById(Long id) {
        return BookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Книга с id = %d не найдена".formatted(id))));
    }

    @Override
    public Page<BookDto> findAll(PageableParam pageableParam) {
        Pageable pageRequest = getPageable(pageableParam);

        Page<Book> books = bookRepository.getAll(pageRequest);

        books.forEach(book -> log.info("Книга {}, автор {}", book.getTitle(), book.getAuthor().getName()));

        return new PageImpl<>(books.stream()
                .map(BookMapper::toDto)
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
    public Page<BookDto> findByAuthor(PageableParam pageableParam, String author) {
        if (author.isBlank()) {
            throw new IllegalArgumentException("Не указан автор");
        }

        Pageable pageRequest = getPageable(pageableParam);

        Page<Book> books = bookRepository.findBookByAuthor_Name(author, pageRequest);

        return new PageImpl<>(books.stream()
                .map(BookMapper::toDto)
                .toList(), books.getPageable(), books.getTotalElements());
    }

    @Override
    public BookDto findByTitleAndAuthor(String title, String author) {
        if (author.isBlank() || title.isBlank()) {
            throw new IllegalArgumentException("Не указан автор или название книги");
        }

        return BookMapper.toDto(bookRepository.findBookByTitleAndAuthor_Name(title, author).orElseThrow(() ->
                new NotFoundException("Не удалось найти книгу %s автора %s".formatted(title, author))));
    }

    @Override
    public List<BookDto> searchByTitle(String text) {
        if (text.isBlank()) {
            return List.of();
        }

        return bookRepository.findByPartOfTitle(text).stream()
                .map(BookMapper::toDto)
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

        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(dto, author)));
    }

    private Pageable getPageable(PageableParam pageableParam) {
        String[] sortParams = pageableParam.getSort().split(",");
        Sort sort;
        if (sortParams.length == 1) {
            sort = Sort.by(sortParams[0]);
        } else {
            switch (sortParams[1]) {
                case "asc" -> sort = Sort.by(Sort.Direction.ASC, sortParams[0]);
                case "desc" -> sort = Sort.by(Sort.Direction.DESC, sortParams[0]);
                default -> sort = Sort.unsorted();
            }
        }
        return PageRequest.of(pageableParam.getPage(), pageableParam.getSize(), sort);
    }
}
