package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sabitov.example.dto.AuthorDto;
import ru.sabitov.example.dto.CreateAuthorDto;
import ru.sabitov.example.mapper.AuthorMapper;
import ru.sabitov.example.repository.AuthorRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorDto create(CreateAuthorDto dto) {
        return AuthorMapper.toDto(authorRepository.save(AuthorMapper.toEntity(dto)));
    }
}
