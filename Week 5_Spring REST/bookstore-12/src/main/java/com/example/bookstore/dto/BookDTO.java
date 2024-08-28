package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookDTO {

    private Long id;

    @JsonProperty("book_title")
    private String title;

    private String author;

    @JsonProperty("price_in_usd")
    private Double price;

    private String isbn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String publicationDate;
}
