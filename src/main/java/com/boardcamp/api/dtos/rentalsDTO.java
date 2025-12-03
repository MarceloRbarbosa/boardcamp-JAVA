package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class rentalsDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long gameId;

    @NotNull
    private Integer daysRented;

}
