package ru.sabitov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @PostMapping("/notify")
    public ResponseEntity<Void> createNotification() {
        log.info("Поступило новое уведомление");

        return ResponseEntity.ok().build();
    }
}
