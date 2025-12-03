package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.customersDTO;
import com.boardcamp.api.exceptions.ConflictException;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.InvalidCpfException;
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

    public customerModel getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    public customerModel postCustomer(customersDTO body) {

        validateName(body);
        validateCpf(body.getCpf());
        validatePhone(body.getPhone());

        customerModel customer = new customerModel(body);

        return customerRepository.save(customer);
    }

    private void validateName(customersDTO dto) {

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new EmptyFieldException("Name must not be null or empty");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.length() < 10 || phone.length() > 11) {
            throw new InvalidCpfException("Phone must have 10 or 11 digits");
        }
    }

    private void validateCpf(String cpf) {

        if (cpf == null || cpf.length() != 11) {
            throw new InvalidCpfException("Cpf must have 11 digits");
        }

        if (customerRepository.existsByCpf(cpf)) {
            throw new ConflictException("This cpf already exists");
        }
    }

    public customerModel getCustomerModelById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

}
