package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.models.gamesModel;
import com.boardcamp.api.services.GamesService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesService gamesService;

    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping()
    public List<gamesDTO> getGames() {
        return gamesService.getGames();
    }

    @PostMapping()
    public ResponseEntity<gamesDTO> postGames(@RequestBody @Valid gamesDTO body) {
        gamesDTO createdGame = gamesService.postGames(body);
        return new ResponseEntity<>(createdGame, HttpStatus.CREATED);
    }

}
