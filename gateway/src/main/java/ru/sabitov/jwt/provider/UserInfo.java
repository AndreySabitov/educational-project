package ru.sabitov.jwt.provider;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public record UserInfo(String username, List<SimpleGrantedAuthority> roles) {
}
