package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sabitov.example.model.OutboxEvent;

import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {
    @Query("SELECT oe FROM OutboxEvent oe WHERE oe.published=false ORDER BY oe.createdAt ASC")
    List<OutboxEvent> findAllNotPublishedEvents();
}