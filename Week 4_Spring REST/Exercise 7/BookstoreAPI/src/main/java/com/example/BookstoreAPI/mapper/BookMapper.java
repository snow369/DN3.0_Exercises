package com.example.BookstoreAPI.mapper;

import com.example.BookstoreAPI.dto.BookDTO;
import com.example.BookstoreAPI.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BookDTO toDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book toEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}
