package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boardcamp.api.services.CustomersService;
import com.boardcamp.api.dtos.customerResponseDTO;
import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.models.customerModel;

import jakarta.validation.Valid;

import java.util.List;

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
    public ResponseEntity<List<customerResponseDTO>> getCustomers() {
        List<customerModel> customers = customerService.getCustomers();
        List<customerResponseDTO> response = customers.stream()
                .map(customer -> new customerResponseDTO(customer))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<customerResponseDTO> getCustomerById(@PathVariable("id") Long id) {
        customerModel customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(new customerResponseDTO(customer));
    }

    @PostMapping()
    public ResponseEntity<customerModel> postCustomer(@RequestBody @Valid customersDTO body) {
        customerModel createdCustomer = customerService.postCustomer(body);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}
