package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class rentalsDTO {

    @NotBlank
    private Integer customerId;

    @NotBlank
    private Integer gameId;

    @NotBlank
    private String rentDate;

    @NotBlank
    private Integer daysRented;

    private Boolean returnDate;

    @NotBlank
    private Double originalPrice;

    @NotBlank
    private Double delayFee;
}
