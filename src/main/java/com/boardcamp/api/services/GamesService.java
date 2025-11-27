package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.models.gamesModel;
import com.boardcamp.api.repositories.GamesRepository;

@Service
public class GamesService {
    final GamesRepository gamesRepository;

    GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public List<gamesModel> getGames() {
        return gamesRepository.findAll();
    }

    public gamesModel postGames(gamesDTO body) {
        gamesModel game = new gamesModel(body);
        gamesRepository.save(game);
        return game;
    }

}
