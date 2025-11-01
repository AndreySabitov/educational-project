package ru.sabitov.example.error;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
