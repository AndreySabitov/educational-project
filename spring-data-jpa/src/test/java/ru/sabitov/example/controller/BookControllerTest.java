package ru.sabitov.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sabitov.example.config.SecurityConfig;
import ru.sabitov.example.dto.BookDto;
import ru.sabitov.example.dto.CreateBookDto;
import ru.sabitov.example.jwt.JwtTokenProvider;
import ru.sabitov.example.service.BookService;

import java.nio.charset.StandardCharsets;
import java.time.Year;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@Import(SecurityConfig.class)
@WithMockUser
class BookControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private BookService bookService;
    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;
    @MockitoBean
    private UserDetailsService userDetailsService;

    private final BookDto bookDto = new BookDto(1L, "Title", "Author", Year.of(2002));

    @Test
    void testGetByIdEndpoint() throws Exception {
        when(bookService.findById(1L)).thenReturn(bookDto);

        mvc.perform(get("/books/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void testCreateBook() throws Exception {
        when(bookService.addBook(any(CreateBookDto.class))).thenReturn(bookDto);

        mvc.perform(post("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateBookDto("Title",
                                "Author", 2002))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void testThrowBadRequest_whenTryCreateBookWithWrongTitle() throws Exception {
        mvc.perform(post("/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateBookDto("T",
                                "Author", 2002))))
                .andExpect(status().isBadRequest());
    }

}