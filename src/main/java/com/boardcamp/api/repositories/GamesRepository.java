package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.dtos.gamesDTO;
import com.boardcamp.api.models.gamesModel;

@Repository
public interface GamesRepository extends JpaRepository<gamesModel, Long> {

    boolean existsByName(String name);

    gamesDTO findByName(String string);

}
