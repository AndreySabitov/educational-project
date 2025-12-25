package ru.sabitov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.dto.BookDto;
import ru.sabitov.dto.CreateBookDto;
import ru.sabitov.mapper.BookMapper;
import ru.sabitov.repository.BookRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookDto createBook(CreateBookDto dto) {
        return BookMapper.toDto(bookRepository.save(BookMapper.toEntity(dto)));
    }

    @Override
    public BookDto getById(Long bookId) {
        return BookMapper.toDto(bookRepository.findById(bookId).orElseThrow());
    }
}
