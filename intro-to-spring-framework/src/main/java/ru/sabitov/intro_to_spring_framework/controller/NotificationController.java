package ru.sabitov.intro_to_spring_framework.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.intro_to_spring_framework.service.NotificationService;

@RestController
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(@Qualifier("EmailNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
