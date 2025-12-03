package com.boardcamp.api.dtos;

import java.time.LocalDate;

import com.boardcamp.api.models.rentalsModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RentalResponseDTO {

    private Long id;
    private LocalDate rentDate;
    private Integer daysRented;
    private LocalDate returnDate;
    private Integer originalPrice;
    private Integer delayFee;
    private customerResponseDTO customer;
    private GameResponseDTO game;

    public RentalResponseDTO(rentalsModel rentalsModel) {
        this.id = rentalsModel.getId();
        this.rentDate = rentalsModel.getRentDate();
        this.daysRented = rentalsModel.getDaysRented();
        this.returnDate = rentalsModel.getReturnDate();
        this.originalPrice = rentalsModel.getOriginalPrice();
        this.delayFee = rentalsModel.getDelayFee();
        this.customer = new customerResponseDTO(rentalsModel.getCustomer());
        this.game = new GameResponseDTO(rentalsModel.getGame());
    }

}
