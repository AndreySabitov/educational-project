package ru.sabitov.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sabitov.example.dto.AuthorDto;
import ru.sabitov.example.dto.CreateAuthorDto;
import ru.sabitov.example.service.AuthorService;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody CreateAuthorDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(dto));
    }

}
