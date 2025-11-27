package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.models.gamesModel;
import com.boardcamp.api.repositories.GamesRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/games")
public class GamesController {

    final GamesRepository gamesRepository;

    GamesController(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @GetMapping()
    public List<gamesModel> getGames() {
        return gamesRepository.findAll();
    }

    @PostMapping()
    public void postGame(@RequestBody @Valid gamesDTO body) {
        gamesModel game = new gamesModel(body);
        gamesRepository.save(game);
    }

}
