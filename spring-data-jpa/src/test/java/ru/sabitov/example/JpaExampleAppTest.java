package ru.sabitov.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.jwt.JwtTokenProvider;
import ru.sabitov.example.model.Author;
import ru.sabitov.example.model.Role;
import ru.sabitov.example.model.User;
import ru.sabitov.example.repository.AuthorRepository;
import ru.sabitov.example.repository.UserRepository;

import java.time.Year;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class JpaExampleAppTest {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @LocalServerPort
    private int port;

    private final BookDto bookDto = new BookDto(1L, "Title", "Author", Year.of(2000));

    @Test
    void test() throws JsonProcessingException {
        userRepository.save(new User(null, "Test", "password", Role.ROLE_USER));
        authorRepository.save(new Author(null, "Author"));

        String url = "http://localhost:" + port + "/books";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String token = jwtTokenProvider.generateToken("Test");
        headers.set("Authorization", "Bearer " + token);

        String body = objectMapper
                .writeValueAsString(new CreateBookDto("Title", "Author", 2000));
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        BookDto result = testRestTemplate.postForEntity(url, request, BookDto.class).getBody();

        assertNotNull(result);
        assertEquals(bookDto, result);
    }
}