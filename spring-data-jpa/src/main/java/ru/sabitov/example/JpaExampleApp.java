package ru.sabitov.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class JpaExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(JpaExampleApp.class, args);
    }
}
