package com.boardcamp.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.customerModel;

@Repository
public interface CustomerRepository extends JpaRepository<customerModel, Long> {

    boolean existsByCpf(String cpf);

    Optional<customerModel> findById(Long id);

}
