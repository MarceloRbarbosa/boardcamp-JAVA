package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.models.customerModel;
import com.boardcamp.api.repositories.CustomerRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id) {
        Optional<customerModel> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este cliente não existe");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(customer.get());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> postCustomer(@RequestBody @Valid customersDTO body) {
        customerModel customer = new customerModel(body);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
}
