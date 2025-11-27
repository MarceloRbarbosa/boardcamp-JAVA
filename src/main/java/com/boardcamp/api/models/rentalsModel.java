package com.boardcamp.api.models;

import com.boardcamp.api.dtos.rentalsDTO;

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
@Table(name = "rentals")
public class rentalsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private Integer gameId;

    @Column(nullable = false)
    private String rentDate;

    @Column(nullable = false)
    private Integer daysRented;

    @Column
    private Boolean returnDate;

    @Column(nullable = false)
    private Double originalPrice;

    @Column(nullable = false)
    private Double delayFee;

    public rentalsModel(rentalsDTO dto) {
        this.customerId = dto.getCustomerId();
        this.gameId = dto.getGameId();
        this.daysRented = dto.getDaysRented();
    }

}
