package com.boardcamp.api.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boardcamp.api.dtos.gamesDTO;

import com.boardcamp.api.exceptions.EmptyNameException;
import com.boardcamp.api.exceptions.GameConflictException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.exceptions.ResourceNotFoundException;
import com.boardcamp.api.models.gamesModel;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.services.GamesService;

@ExtendWith(MockitoExtension.class)
public class gamesUnitTests {

    @InjectMocks
    private GamesService gamesService;

    @Mock
    private GamesRepository gamesRepository;

    @Mock
    private gamesModel gamesModel;

    @Test
    void givenWrongUserId_whenGetGameById_thenThrowsError() {
        Long id = 0L;

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> gamesService.getGameById(id));

        assertEquals("Game not found", ex.getMessage());
    }

    @Test
    void givenRepeatedGameName_whenCreatingNewGame_thenThrowsError() {
        gamesDTO game = new gamesDTO(
                "war",
                "https://",
                5,
                110);

        when(gamesRepository.existsByName(anyString()))
                .thenReturn(true);

        GameConflictException ex = assertThrows(
                GameConflictException.class,
                () -> gamesService.postGames(game));

        assertEquals("This game already exist", ex.getMessage());

    }

    @Test
    void givenNegativeValue_whenGetStockTotal_thenThrowsError() {
        gamesDTO game = new gamesDTO(
                "war",
                "https://",
                0,
                110);

        NegativeValueException ex = assertThrows(
                NegativeValueException.class,
                () -> gamesService.postGames(game));

        assertEquals("Stock total and pricePerDay is a positive value", ex.getMessage());

    }

    @Test
    void givenNegativeValue_whenPricePerDay_thenThrowsError() {
        gamesDTO game = new gamesDTO(
                "war",
                "https://",
                10,
                0);

        NegativeValueException ex = assertThrows(
                NegativeValueException.class,
                () -> gamesService.postGames(game));

        assertEquals("Stock total and pricePerDay is a positive value", ex.getMessage());

    }

    @Test
    void givenEmptyFieldName_WhenCreatingGame_thenThrowsInvalidName() {
        gamesDTO game = new gamesDTO(
                "",
                "https://",
                10,
                0);

        EmptyNameException ex = assertThrows(
                EmptyNameException.class,
                () -> gamesService.postGames(game));

        assertEquals("Name must not be null or empty ", ex.getMessage());
    }
}
