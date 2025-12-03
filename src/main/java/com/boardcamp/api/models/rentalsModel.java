package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.dtos.rentalsDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "rentDate")
    private LocalDate rentDate;

    @Column(name = "daysRented")
    private Integer daysRented;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "originalPrice")
    private Integer originalPrice;

    @Column(name = "delayFee")
    private Integer delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private customerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private gamesModel game;

    public rentalsModel(rentalsDTO dto) {
        this.daysRented = dto.getDaysRented();
        this.rentDate = LocalDate.now();
        this.originalPrice = game.getPricePerDay() * dto.getDaysRented();
        this.returnDate = null;
        this.delayFee = 0;
    }

}
