package com.example.BookstoreAPI.repository;

import com.example.BookstoreAPI.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<>();
    private Long currentId = 1L;

    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    public Optional<Book> findById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(currentId++);
        }
        books.removeIf(b -> b.getId().equals(book.getId()));
        books.add(book);
        return book;
    }

    public void deleteById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
