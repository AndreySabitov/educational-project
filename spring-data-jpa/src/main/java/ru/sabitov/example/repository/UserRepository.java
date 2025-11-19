package ru.sabitov.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sabitov.example.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}