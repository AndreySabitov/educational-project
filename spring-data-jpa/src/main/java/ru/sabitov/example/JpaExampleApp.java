package ru.sabitov.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JpaExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApp.class, args);
    }
}
