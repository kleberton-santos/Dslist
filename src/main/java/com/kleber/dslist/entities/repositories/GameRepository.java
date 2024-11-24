package com.kleber.dslist.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kleber.dslist.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
