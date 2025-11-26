package ru.sabitov.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {
    private final RestClient restClient;

    public NotificationClient(@Value("${notification-service.url}") String url) {
        this.restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public void sendNotification() {
        restClient.post()
                .uri("/notify")
                .retrieve()
                .toEntity(Void.class);
    }
}
