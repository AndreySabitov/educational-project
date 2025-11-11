package ru.sabitov.example.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.example.dto.AuthorDto;
import ru.sabitov.example.dto.CreateAuthorDto;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;

@UtilityClass
public class AuthorMapper {

    public Author toEntity(CreateAuthorDto dto) {
        return new Author(null, dto.getName());
    }

    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getBooks().stream()
                .map(Book::getTitle)
                .toList());
    }
}
