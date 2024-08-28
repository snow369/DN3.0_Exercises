package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // CREATE a new book
    @PostMapping
    public ResponseEntity<EntityModel<BookDTO>> createBook(@RequestBody BookDTO bookDTO) {
        Book book = BookMapper.INSTANCE.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        BookDTO savedBookDTO = BookMapper.INSTANCE.toDTO(savedBook);

        // Add HATEOAS links
        EntityModel<BookDTO> resource = EntityModel.of(savedBookDTO);
        resource.add(linkTo(methodOn(BookController.class).getBookById(savedBook.getId())).withSelfRel());
        resource.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

        return ResponseEntity.status(201).body(resource);
    }

    // READ all books
    @GetMapping
    public List<EntityModel<BookDTO>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = BookMapper.INSTANCE.toDTO(book);
                    EntityModel<BookDTO> resource = EntityModel.of(bookDTO);
                    resource.add(linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
    }

    // READ a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        BookDTO bookDTO = BookMapper.INSTANCE.toDTO(book);

        // Add HATEOAS links
        EntityModel<BookDTO> resource = EntityModel.of(bookDTO);
        resource.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
        resource.add(linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));

        return ResponseEntity.ok(resource);
    }
}
