package ru.sabitov.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.dto.BookDto;
import ru.sabitov.dto.CreateBookDto;
import ru.sabitov.model.Book;

@UtilityClass
public class BookMapper {

    public Book toEntity(CreateBookDto dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        return book;
    }

    public BookDto toDto(Book book) {
        return  new BookDto(book.getId(), book.getTitle(), book.getAuthor());
    }
}
