package ru.sabitov.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncDemoService {

    @Async
    public void doTask() {
        try {
            log.info("Начало выполнения задачи");
            Thread.sleep(5000);
            log.info("Задача завершена");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
