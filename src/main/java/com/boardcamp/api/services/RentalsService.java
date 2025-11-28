package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.models.rentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalsService {

    final RentalsRepository rentalsRepository;

    RentalsService(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    public List<rentalsModel> getRentals() {
        return rentalsRepository.findAll();
    }

    public rentalsModel postRental(rentalsDTO body) {
        rentalsModel rental = new rentalsModel(body);
        rentalsRepository.save(rental);
        return rental;
    }

    public void deleteRental(Long id) {
        rentalsRepository.deleteById(id);
    }
}
