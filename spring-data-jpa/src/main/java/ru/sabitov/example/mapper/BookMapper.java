package ru.sabitov.example.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "publicationYear", expression = "java(Year.of(dto.getPublicationYear()))")
    Book toEntity(CreateBookDto dto, Author author);

    @Mapping(target = "author", source = "book.author.name")
    BookDto toDto(Book book);
}
