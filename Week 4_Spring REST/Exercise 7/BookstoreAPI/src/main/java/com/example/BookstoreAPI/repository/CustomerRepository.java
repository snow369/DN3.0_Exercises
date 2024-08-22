package com.example.BookstoreAPI.repository;

import com.example.BookstoreAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // JpaRepository already includes methods like findById, save, existsById, deleteById, etc.
}
