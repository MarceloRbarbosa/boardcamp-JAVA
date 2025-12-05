package com.boardcamp.api.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalResponseDTO;
import com.boardcamp.api.dtos.rentalsDTO;
import com.boardcamp.api.exceptions.EmptyFieldException;
import com.boardcamp.api.exceptions.GameUnavailableException;
import com.boardcamp.api.exceptions.InvalidGameIdException;
import com.boardcamp.api.exceptions.NegativeValueException;
import com.boardcamp.api.exceptions.ResourceNotFoundException;
import com.boardcamp.api.exceptions.UnprocessableEntityException;
import com.boardcamp.api.models.customerModel;
import com.boardcamp.api.models.gamesModel;
import com.boardcamp.api.models.rentalsModel;

import com.boardcamp.api.repositories.RentalsRepository;

@Service
public class RentalsService {

    final CustomersService customerService;
    final RentalsRepository rentalsRepository;
    final GamesService gamesService;

    RentalsService(RentalsRepository rentalsRepository, CustomersService customerService, GamesService gamesService) {
        this.rentalsRepository = rentalsRepository;
        this.customerService = customerService;
        this.gamesService = gamesService;
    }

    public List<RentalResponseDTO> getRentals() {
        List<rentalsModel> rentals = rentalsRepository.findAll();

        return rentals.stream()
                .map(RentalResponseDTO::new)
                .toList();
    }

    public RentalResponseDTO postRental(rentalsDTO body) {

        validateRentalDTO(body);

        gamesModel game = gamesService.getGameById(body.getGameId());
        customerModel customer = customerService.getCustomerModelById(body.getCustomerId());

        validateGameAvailability(game);

        LocalDate rentDate = LocalDate.now();
        Integer originalPrice = calculateOriginalPrice(game.getPricePerDay(), body.getDaysRented());

        rentalsModel rental = new rentalsModel();
        rental.setRentDate(rentDate);
        rental.setDaysRented(body.getDaysRented());
        rental.setReturnDate(null);
        rental.setOriginalPrice(originalPrice);
        rental.setDelayFee(0);
        rental.setCustomer(customer);
        rental.setGame(game);

        rentalsModel saved = rentalsRepository.save(rental);
        return new RentalResponseDTO(saved);
    }

    private void validateRentalDTO(rentalsDTO body) {
        if (body.getDaysRented() <= 0) {
            throw new NegativeValueException("Days rented must be have a positive value");
        }

        if (body.getCustomerId() == null || body.getGameId() == null) {
            throw new EmptyFieldException("Customer and gameId not be null");
        }

        if (body.getGameId() <= 0) {
            throw new InvalidGameIdException("Game Id not valid");
        }
    }

    private void validateGameAvailability(gamesModel game) {
        Integer stockTotal = game.getStockTotal();
        Long rentedCount = rentalsRepository.countByGameIdAndReturnDateIsNull(game.getId());

        if (rentedCount >= stockTotal) {
            throw new GameUnavailableException("No availability for the selected game.");
        }
    }

    private Integer calculateOriginalPrice(Integer pricePerDay, Integer daysRented) {
        return pricePerDay * daysRented;
    }

    public void deleteRental(Long id) {
        rentalsModel rental = rentalsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found"));

        if (rental.getReturnDate() != null) {
            throw new NegativeValueException("Cannot delete a rental that has been returned.");
        }

        rentalsRepository.delete(rental);
    }

    public RentalResponseDTO returnRental(Long id) {
        rentalsModel returnModel = rentalsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));

        if (returnModel.getReturnDate() != null) {
            throw new UnprocessableEntityException("Rental is already returned.");
        }

        LocalDate returnDate = LocalDate.now();
        returnModel.setReturnDate(returnDate);

        LocalDate rentDate = returnModel.getRentDate();

        int daysRented = returnModel.getDaysRented();
        int pricePerDay = returnModel.getGame().getPricePerDay();

        LocalDate expectedReturnDate = rentDate.plusDays(daysRented);

        int delayDays = 0;

        if (returnDate.isAfter(expectedReturnDate)) {
            delayDays = (int) ChronoUnit.DAYS.between(expectedReturnDate, returnDate);
        }

        int delayFee = Math.max(0, delayDays * pricePerDay);

        returnModel.setDelayFee(delayFee);

        rentalsModel savedRental = rentalsRepository.save(returnModel);

        return new RentalResponseDTO(savedRental);
    }

}
