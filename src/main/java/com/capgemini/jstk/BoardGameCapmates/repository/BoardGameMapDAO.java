package com.capgemini.jstk.BoardGameCapmates.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capgemini.jstk.BoardGameCapmates.exceptions.BoardGameAlreadyExistsInDatabase;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotExistingBoardGameException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;

@Repository
public class BoardGameMapDAO {
	private Map<String, BoardGame> gameMap;
	
	BoardGameMapDAO() {
		this.gameMap = new HashMap<>();
	}
	
	public void addBoardGame(BoardGame boardGame) throws BoardGameAlreadyExistsInDatabase{
		if(gameMap.containsKey(boardGame.getName())){
			throw new BoardGameAlreadyExistsInDatabase();
		}
		
		gameMap.put(boardGame.getName(), boardGame);
	}
	
	public void deleteBoardGame(BoardGame boardGame) throws BoardGameAlreadyExistsInDatabase, NotExistingBoardGameException{
		if(!gameMap.containsKey(boardGame.getName())){
			throw new NotExistingBoardGameException();
		}
		
		gameMap.remove(boardGame.getName());
	}
}
