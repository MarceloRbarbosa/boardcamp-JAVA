package com.boardcamp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.models.customerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomersService {

    final CustomerRepository customerRepository;

    CustomersService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<customerModel> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<customerModel> getCustomerById(Long id) {
        Optional<customerModel> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return Optional.empty();
        } else {
            return customer;
        }
    }

    public customerModel postCustomer(customersDTO body) {
        customerModel customer = new customerModel(body);
        customerRepository.save(customer);
        return customer;
    }
}
