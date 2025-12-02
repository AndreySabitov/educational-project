package ru.sabitov.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {
    private final RestClient rest;
    @Value("${notification-service.url}")
    private String notificationServiceUrl;

    public NotificationClient(RestClient.Builder builder) {
        this.rest = builder.build();
    }

    public void sendNotification() {
        rest.post()
                .uri(notificationServiceUrl + "/notify")
                .retrieve()
                .toEntity(Void.class);
    }
}
