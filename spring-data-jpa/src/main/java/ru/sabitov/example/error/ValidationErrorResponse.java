package ru.sabitov.example.error;

import java.util.List;

public record ValidationErrorResponse(List<FieldErrorDescription> errors) {
}
