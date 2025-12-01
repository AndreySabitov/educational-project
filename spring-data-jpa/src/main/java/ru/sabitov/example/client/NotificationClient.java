package ru.sabitov.example.client;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
public class NotificationClient {
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;

    private static final String NOTIFICATION_SERVICE_ID = "NOTIFICATION-SERVICE";

    public NotificationClient(EurekaDiscoveryClient discoveryClient) {
        this.restClient = RestClient.builder().build();
        this.discoveryClient = discoveryClient;
    }

    public void sendNotification() {
        URI notificationUri = discoveryClient.getInstances(NOTIFICATION_SERVICE_ID).getFirst().getUri();

        restClient.post()
                .uri(notificationUri + "/notify")
                .retrieve()
                .toEntity(Void.class);
    }
}
