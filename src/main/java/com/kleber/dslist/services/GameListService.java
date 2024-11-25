package com.kleber.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kleber.dslist.entities.GameList;
import com.kleber.dslist.entities.dto.GameListDTO;
import com.kleber.dslist.entities.dto.GameMinDTO;
import com.kleber.dslist.entities.repositories.GameListRepository;
import com.kleber.dslist.entities.repositories.GameRepository;
import com.kleber.dslist.projection.GameMinProjection;


@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true )
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
		
	}

}
