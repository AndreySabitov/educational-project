package ru.sabitov.example.client;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationFeignClient {

    @PostMapping("/notify")
    @CircuitBreaker(name = "notificationService", fallbackMethod = "createNotificationFallback")
    @Retry(name = "notificationService")
    ResponseEntity<Void> createNotification() throws FeignException;

    @PostMapping("/notify")
    default ResponseEntity<Void> createNotificationFallback(Throwable throwable) {
        return ResponseEntity.ok().build();
    }
}
