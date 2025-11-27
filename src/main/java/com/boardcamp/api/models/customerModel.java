package com.boardcamp.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class customerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Size(min = 10, max = 11, message = "O Telefone deve ter entre 10 e  11 caracteres")
    @Column(nullable = false)
    private String phone;

    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 caracteres")
    @Column(length = 11, nullable = false)
    private String cpf;
}
