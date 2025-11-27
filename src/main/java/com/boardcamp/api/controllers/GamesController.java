package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/games")
public class GamesController {

    @GetMapping()
    public String getGames() {
        return "Lista de jogos";
    }

    @PostMapping()
    public String postGame(@RequestBody String body) {
        return body;
    }

}
