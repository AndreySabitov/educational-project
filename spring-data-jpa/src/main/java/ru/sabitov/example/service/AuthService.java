package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.RegisterRequest;
import ru.sabitov.example.dto.UserDto;
import ru.sabitov.example.mapper.UserMapper;
import ru.sabitov.example.model.Role;
import ru.sabitov.example.model.User;
import ru.sabitov.example.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserDto saveUser(RegisterRequest request) {
        User user = new User(null, request.getUsername(), passwordEncoder.encode(request.getPassword()),
                Role.ROLE_USER);

        return userMapper.toDto(userRepository.save(user));
    }
}
