package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boardcamp.api.models.gamesModel;

@Repository
public interface GamesRepository extends JpaRepository<gamesModel, Long> {

}
