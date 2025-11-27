package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.models.rentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getRentals() {
        return ResponseEntity.status(HttpStatus.OK).body(rentalsRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<Object> postRental(@RequestBody @Valid rentalsDTO body) {
        rentalsModel rental = new rentalsModel(body);
        rentalsRepository.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @PostMapping("/{id}/return")
    public String finishRental(@PathVariable("id") Long id, @RequestBody String body) {
        // TO DO implementar essa funcionabilidade
        // ########################################################################
        return "entrega do aluguel" + body;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") Long id) {
        Optional<rentalsModel> rental = rentalsRepository.findById(id);

        if (!rental.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este cliente não existe");
        } else {
            rentalsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

}
