package ru.sabitov.example.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Book;

@UtilityClass
public class BookMapper {

    public Book toEntity(CreateBookDto dto, Author author) {
        return new Book(null, dto.getTitle(), author, dto.getPublicationYear());
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getName(), book.getPublicationYear());
    }
}
