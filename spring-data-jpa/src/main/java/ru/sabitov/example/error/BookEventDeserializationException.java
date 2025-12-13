package ru.sabitov.example.error;

public class BookEventDeserializationException extends RuntimeException {
    public BookEventDeserializationException(String message) {
        super(message);
    }
}
