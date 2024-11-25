package com.kleber.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kleber.dslist.entities.Game;
import com.kleber.dslist.entities.dto.GameDTO;
import com.kleber.dslist.entities.dto.GameMinDTO;
import com.kleber.dslist.entities.repositories.GameRepository;
import com.kleber.dslist.projection.GameMinProjection;


@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true )
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);
	}
	
	@Transactional(readOnly = true )
	public List<GameMinDTO> findAll(){
		var result = gameRepository.findAll();
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	
	}
	
	@Transactional(readOnly = true )
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	
	}

}
