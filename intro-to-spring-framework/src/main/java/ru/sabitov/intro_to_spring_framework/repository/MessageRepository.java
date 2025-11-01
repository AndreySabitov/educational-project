package ru.sabitov.intro_to_spring_framework.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {
    @Value("${app.repositoryMessage}")
    private String message;

    public String getMessage() {
        return message;
    }
}
