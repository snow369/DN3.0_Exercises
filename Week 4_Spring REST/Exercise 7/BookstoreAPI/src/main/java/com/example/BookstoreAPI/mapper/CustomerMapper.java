package com.example.BookstoreAPI.mapper;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}
