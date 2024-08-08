package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

public class LibraryManagementApp {

    @Repository
    public static class BookRepository {
        public void save() {
            System.out.println("Saving book to the repository");
        }
    }

    @Service
    public static class BookService {
        private BookRepository bookRepository;

        public void setBookRepository(BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }

        public void manageBooks() {
            System.out.println("Managing books in the library");
            bookRepository.save();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean(BookService.class);
        bookService.manageBooks();

        // Expected Output:
        // Managing books in the library
        // Saving book to the repository
    }
}
