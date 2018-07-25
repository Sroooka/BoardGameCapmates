package com.capgemini.jstk.BoardGameCapmates.repository;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.jstk.BoardGameCapmates.exceptions.BoardGameAlreadyExistsInDatabase;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotExistingBoardGameException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;

public class BoardGameListDAO {
	private Map<String, BoardGame> gameMap;
	
	BoardGameListDAO() {
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
