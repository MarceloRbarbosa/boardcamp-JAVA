package com.boardcamp.api.dtos;

import com.boardcamp.api.models.gamesModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDTO {

    private Long id;
    private String name;
    private String image;
    private Integer stockTotal;
    private Integer pricePerDay;

    public GameResponseDTO(gamesModel gameModel) {
        this.id = gameModel.getId();
        this.name = gameModel.getName();
        this.image = gameModel.getImage();
        this.stockTotal = gameModel.getStockTotal();
        this.pricePerDay = gameModel.getPricePerDay();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getStockTotal() {
        return stockTotal;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }
}
