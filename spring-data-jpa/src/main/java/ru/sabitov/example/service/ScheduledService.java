package ru.sabitov.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledService {

    @Scheduled(cron = "0 * * * * *")
    public void runTask() {
        log.info("Running scheduled task...");
    }
}
