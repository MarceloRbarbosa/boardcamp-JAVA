package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.models.rentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;
import com.boardcamp.api.services.RentalsService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    private final RentalsService rentalsService;

    public RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @GetMapping
    public ResponseEntity<List<rentalsDTO>> getRentals() {
        List<rentalsDTO> rentals = rentalsService.getRentals();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping
    public ResponseEntity<rentalsModel> postRental(@RequestBody @Valid rentalsDTO body) {
        rentalsModel createdRental = rentalsService.postRental(body);
        return new ResponseEntity<>(createdRental, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<rentalsDTO> returnRental(@PathVariable("id") Long id) {
        rentalsDTO rentalsDTO = rentalsService.returnRental(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") Long id) {
        rentalsService.deleteRental(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
