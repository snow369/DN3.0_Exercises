package com.example.BookstoreAPI.repository;

import com.example.BookstoreAPI.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();
    private Long currentId = 1L;

    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    public Optional<Customer> findById(Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(currentId++);
        }
        customers.removeIf(c -> c.getId().equals(customer.getId()));
        customers.add(customer);
        return customer;
    }
}
