package ru.sabitov.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sabitov.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}