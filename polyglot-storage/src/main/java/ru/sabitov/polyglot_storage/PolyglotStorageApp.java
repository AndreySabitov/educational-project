package ru.sabitov.polyglot_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PolyglotStorageApp {
    public static void main(String[] args) {
        SpringApplication.run(PolyglotStorageApp.class, args);
    }
}
