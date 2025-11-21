package ru.sabitov.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sabitov.example.dto.LoginRequest;
import ru.sabitov.example.dto.RegisterRequest;
import ru.sabitov.example.dto.TokenResponse;
import ru.sabitov.example.dto.UserDto;
import ru.sabitov.example.jwt.JwtTokenProvider;
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public UserDto saveUser(RegisterRequest request) {
        User user = new User(null, request.getUsername(), passwordEncoder.encode(request.getPassword()),
                Role.ROLE_USER);

        return userMapper.toDto(userRepository.save(user));
    }

    public TokenResponse login(LoginRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(token);
        return new TokenResponse(tokenProvider.generateToken(request.getUsername()));
    }
}
