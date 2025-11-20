package ru.sabitov.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.dto.LoginRequest;
import ru.sabitov.example.dto.RegisterRequest;
import ru.sabitov.example.dto.TokenResponse;
import ru.sabitov.example.dto.UserDto;
import ru.sabitov.example.jwt.JwtTokenProvider;
import ru.sabitov.example.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Запрос на регистрацию пользователя {}", request.getUsername());
        return ResponseEntity.ok(authService.saveUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Запрос на вход от пользователя {}", request.getUsername());
        var token = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        return ResponseEntity.ok(
                new TokenResponse(tokenProvider.generateToken(authentication.getPrincipal().toString())));
    }
}
