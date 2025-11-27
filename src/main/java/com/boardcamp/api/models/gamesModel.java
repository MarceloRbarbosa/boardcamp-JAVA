package com.boardcamp.api.models;

import com.boardcamp.api.dtos.gamesDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class gamesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer stockTotal;

    @Column(nullable = false)
    private double pricePerDay;

    public gamesModel(gamesDTO dto) {
        this.name = dto.getName();
        this.image = dto.getImage();
        this.pricePerDay = dto.getPricePerDay();
        this.stockTotal = dto.getStockTotal();
    }
}
