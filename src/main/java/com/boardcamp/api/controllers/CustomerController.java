package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping()
    public String getCustomers() {
        return "Get customer";
    }

    @GetMapping("/{id}")
    public String getCustomerById(@PathVariable("id") Long id) {
        return "cliente de id " + id;
    }

    @PostMapping()
    public String postCustomer(@RequestBody String body) {
        return body;
    }
}
