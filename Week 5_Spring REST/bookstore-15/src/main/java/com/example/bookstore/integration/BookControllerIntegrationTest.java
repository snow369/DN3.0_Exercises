package com.example.bookstore.integration;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Effective Java", "Joshua Bloch", 45.00, "1234567890");
        bookRepository.save(book);
    }

    @Test
    public void testGetBookById() throws Exception {
        Optional<Book> savedBook = bookRepository.findById(book.getId());

        mockMvc.perform(get("/books/{id}", savedBook.get().getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"))
                .andExpect(jsonPath("$.price").value(45.00))
                .andExpect(jsonPath("$.isbn").value("1234567890"));
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{\"title\":\"Clean Code\",\"author\":\"Robert C. Martin\",\"price\":40.00,\"isbn\":\"0987654321\"}";

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        book.setPrice(50.00);
        String updatedBookJson = "{\"title\":\"Effective Java\",\"author\":\"Joshua Bloch\",\"price\":50.00,\"isbn\":\"1234567890\"}";

        mockMvc.perform(put("/books/{id}", book.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(50.00));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", book.getId()))
                .andExpect(status().isNoContent());
    }
}
