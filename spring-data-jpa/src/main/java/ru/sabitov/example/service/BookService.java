package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.mapper.BookMapper;
import ru.sabitov.example.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public BookDto addBook(CreateBookDto dto) {
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(dto)));
    }

    public BookDto findById(Long id) {
        return BookMapper.toDto(bookRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Книга с id = %d не найдена".formatted(id))));
    }

    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
