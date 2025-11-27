package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.models.customerModel;
import com.boardcamp.api.repositories.CustomerRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    final CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping()
    public List<customerModel> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<customerModel> getCustomerById(@PathVariable("id") Long id) {
        Optional<customerModel> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(customer.get());
        }
    }

    @PostMapping()
    public void postCustomer(@RequestBody @Valid customersDTO body) {
        customerModel customer = new customerModel(body);
        customerRepository.save(customer);
    }
}
