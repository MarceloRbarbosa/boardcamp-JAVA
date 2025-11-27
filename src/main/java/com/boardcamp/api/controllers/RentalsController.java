package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.models.rentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    final RentalsRepository rentalsRepository;

    RentalsController(RentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @GetMapping()
    public List<rentalsModel> getRentals() {
        return rentalsRepository.findAll();
    }

    @PostMapping()
    public void postRental(@RequestBody @Valid rentalsDTO body) {
        rentalsModel rental = new rentalsModel(body);
        rentalsRepository.save(rental);
    }

    @PostMapping("/{id}/return")
    public String putMethodName(@PathVariable("id") Long id, @RequestBody String body) {

        return "entrega do aluguel" + body;
    }

    @DeleteMapping("/{id}")
    public String deleteRental(@PathVariable("id") Long id) {
        return "Deletando " + id;
    }

}
