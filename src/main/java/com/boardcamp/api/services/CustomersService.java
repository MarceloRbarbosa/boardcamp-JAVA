package com.boardcamp.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.ResourceNotFoundException;
import com.boardcamp.api.models.customerModel;
import com.boardcamp.api.repositories.CustomerRepository;

@Service
public class CustomersService {

    private final CustomerRepository customerRepository;

    public CustomersService(CustomerRepository customerRepository) {
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

        validateName(body);
        validateCpf(body.getCpf());

        customerModel customer = new customerModel(body);
        customer.setName(body.getName());
        customer.setCpf(body.getCpf());

        return customerRepository.save(customer);
    }

    private void validateName(customersDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EmptyFieldException("Name must not be null or empty");
        }
    }

    private void validateCpf(String cpf) {

        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("Cpf must have 11 digits");
        }

        if (CustomerRepository.existsByCpf(cpf)) {
            throw new ConflictException("This cpf already exists");
        }
    }

    public customerModel getCustomerModelById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

}
