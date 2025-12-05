package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class gamesDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "image must not be blank")
    private String image;

    @NotNull(message = "StockTotal must not be null")
    private Integer stockTotal;

    @NotNull(message = "pricePerDay must not be null")
    private Integer pricePerDay;
}
