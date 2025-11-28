package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.models.rentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;
import com.boardcamp.api.services.RentalsService;
import jakarta.validation.Valid;

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

    private final RentalsService rentalsService;

    final RentalsRepository rentalsRepository;

    RentalsController(RentalsRepository rentalsRepository, RentalsService rentalsService) {
        this.rentalsRepository = rentalsRepository;
        this.rentalsService = rentalsService;
    }

    @GetMapping()
    public ResponseEntity<Object> getRentals() {
        return ResponseEntity.status(HttpStatus.OK).body(rentalsService.getRentals());
    }

    @PostMapping()
    public ResponseEntity<Object> postRental(@RequestBody @Valid rentalsDTO body) {
        rentalsModel rental = rentalsService.postRental(body);
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
        rentalsService.deleteRental(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
