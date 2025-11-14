package ru.sabitov.polyglot_storage.errors;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}
