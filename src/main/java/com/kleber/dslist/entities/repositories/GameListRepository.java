package com.kleber.dslist.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kleber.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
