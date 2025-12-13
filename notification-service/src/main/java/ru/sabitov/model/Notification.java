package ru.sabitov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notifications")
@AllArgsConstructor
@Getter
public class Notification {
    @Id
    private String id;

    private String title;

    private String author;

    private Instant timestamp;
}
