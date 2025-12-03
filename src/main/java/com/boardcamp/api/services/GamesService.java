package com.boardcamp.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.exceptions.EmptyNameException;
import com.boardcamp.api.exceptions.GameConflictException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.exceptions.ResourceNotFoundException;
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

        if (body.getName() == null || body.getName().isEmpty()) {
            throw new EmptyNameException("Name must not be null or empty ");
        }

        if (body.getStockTotal() <= 0 || body.getPricePerDay() <= 0) {
            throw new NegativeValueException("Stock total and pricePerDay is a positive value");
        }

        if (gamesRepository.existsByName(body.getName())) {
            throw new GameConflictException("This game already exist");
        }

        gamesModel game = new gamesModel(body);
        gamesRepository.save(game);
        return game;

    }

    public gamesModel getGameById(Long id) {
        return gamesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game not found"));
    }

}
