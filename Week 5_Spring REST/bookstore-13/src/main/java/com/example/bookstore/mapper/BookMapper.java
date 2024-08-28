package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
}


