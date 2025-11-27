package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

    @GetMapping()
    public String getRentals() {
        return "get rentals";
    }

    @PostMapping()
    public String postRental(@RequestBody String body) {
        return body;
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
