package com.boardcamp.api.dtos;

import com.boardcamp.api.models.customerModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class customerResponseDTO {

    private Long id;
    private String name;
    private String cpf;
    private String phone;

    public customerResponseDTO(customerModel customerModel) {
        this.id = customerModel.getId();
        this.name = customerModel.getName();
        this.cpf = customerModel.getCpf();
        this.phone = customerModel.getPhone();
    }
}
