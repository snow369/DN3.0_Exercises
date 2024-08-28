package com.example.bookstore.controller;

import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.exception.ResourceNotFoundException;
import com.example.bookstore.mapper.CustomerMapper;
import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // CREATE a new customer
    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = CustomerMapper.INSTANCE.toDTO(savedCustomer);

        // Add HATEOAS links
        EntityModel<CustomerDTO> resource = EntityModel.of(savedCustomerDTO);
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(savedCustomer.getId())).withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));

        return ResponseEntity.status(201).body(resource);
    }

    // READ all customers
    @GetMapping
    public List<EntityModel<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> {
                    CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDTO(customer);
                    EntityModel<CustomerDTO> resource = EntityModel.of(customerDTO);
                    resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
    }

    // READ a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDTO(customer);

        // Add HATEOAS links
        EntityModel<CustomerDTO> resource = EntityModel.of(customerDTO);
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));

        return ResponseEntity.ok(resource);
    }
}
