package ru.sabitov.example.error;

public class BookEventSerializationException extends RuntimeException {
    public BookEventSerializationException(String message) {
        super(message);
    }
}
