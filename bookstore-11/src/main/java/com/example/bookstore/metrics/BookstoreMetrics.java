package com.example.bookstore.metrics;

import com.example.bookstore.repository.BookRepository;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookstoreMetrics {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        meterRegistry.gauge("books.count", bookRepository.count());
    }
}
