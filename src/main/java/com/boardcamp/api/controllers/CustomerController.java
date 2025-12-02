package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardcamp.api.services.CustomersService;
import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.models.customerModel;

import jakarta.validation.Valid;

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

    private final CustomersService customerService;

    public CustomerController(CustomersService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<customerModel> getCustomers() {
        customerModel customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<customerModel> getCustomerById(@PathVariable("id") Long id) {
        customerModel customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<customerModel> postCustomer(@RequestBody @Valid customersDTO body) {
        customerModel createdCustomer = customerService.postCustomer(body);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}
