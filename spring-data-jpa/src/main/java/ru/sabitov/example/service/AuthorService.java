package ru.sabitov.example.service;

import ru.sabitov.example.dto.AuthorDto;
import ru.sabitov.example.dto.CreateAuthorDto;

public interface AuthorService {
    AuthorDto create(CreateAuthorDto dto);
}
