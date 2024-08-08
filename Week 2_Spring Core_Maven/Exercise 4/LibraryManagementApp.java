package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApp {

    public static class BookRepository {
        public void save() {
            System.out.println("Saving book to the repository");
        }
    }

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
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.manageBooks();

        // Expected Output:
        // Before executing: void com.library.BookService.manageBooks()
        // Managing books in the library
        // Saving book to the repository
        // After executing: void com.library.BookService.manageBooks()
    }
}
