package com.boardcamp.api.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.InvalidGameIdException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import com.boardcamp.api.services.CustomersService;
import com.boardcamp.api.services.GamesService;
import com.boardcamp.api.services.RentalsService;

@ExtendWith(MockitoExtension.class)
public class RentalsUnitTests {

        @InjectMocks
        private RentalsService rentalsService;

        @Mock
        private RentalsRepository rentalsRepository;

        @Mock
        private CustomersService customersService;

        @Mock
        private CustomerRepository customerRepository;

        @Mock
        GamesService gamesService;

        @Test
        void givenDaysRentedZero_whenPostRental_thenThrowsNegativeValueException() {

                rentalsDTO dto = new rentalsDTO(
                                1L,
                                1L,
                                0);

                NegativeValueException ex = assertThrows(
                                NegativeValueException.class,
                                () -> rentalsService.postRental(dto));

                assertEquals(
                                "Days rented must be have a positive value",
                                ex.getMessage());
        }

        @Test
        void givenNullFields_whenPostRental_thenThrowEmptyFieldException() {
                rentalsDTO dto = new rentalsDTO(null,
                                null,
                                5);

                EmptyFieldException ex = assertThrows(
                                EmptyFieldException.class,
                                () -> rentalsService.postRental(dto));

                assertEquals("Customer and gameId not be null",
                                ex.getMessage());
        }

        @Test
        void givenInvalidGameId_whenPostRental_thenThrowsInvalidGameIdException() {

                rentalsDTO dto = new rentalsDTO(1L, 0L, 3);

                assertThrows(
                                InvalidGameIdException.class,
                                () -> rentalsService.postRental(dto));
        }
}
