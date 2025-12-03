package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class customersDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "phone must not be blank")
    @Size(min = 10, max = 11)
    private String phone;

    @NotBlank(message = "Cpf must not be blank")
    @Size(min = 11, max = 11)
    private String cpf;
}
