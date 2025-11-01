package ru.sabitov.example.mapper;

import lombok.experimental.UtilityClass;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.model.Book;

@UtilityClass
public class BookMapper {

    public Book mapToBook(CreateBookDto dto) {
        return new Book(1L, dto.getTitle(), dto.getAuthor());
    }

}
