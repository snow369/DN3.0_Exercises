package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void manageBooks() {
        System.out.println("Managing books in the library");
        bookRepository.save();
    }
}

class BookRepository {
    public void save() {
        System.out.println("Saving book to the repository");
    }
}

public class LibraryManagementApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.manageBooks();

        // Expected Output:
        // Managing books in the library
        // Saving book to the repository
    }
}
