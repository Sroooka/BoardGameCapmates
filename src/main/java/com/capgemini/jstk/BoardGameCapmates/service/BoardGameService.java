package com.capgemini.jstk.BoardGameCapmates.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.exceptions.BoardGameAlreadyExistsInDatabase;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotExistingBoardGameException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.repository.BoardGameListDAO;

@Service
public class BoardGameService {

	private final BoardGameListDAO boardGameDAO;

	@Autowired
	BoardGameService(BoardGameListDAO boardGameDAO) {
		System.out.println("Im in player service constructor");
		this.boardGameDAO = boardGameDAO;

	}
	
	@PostConstruct
	private void init() throws BoardGameAlreadyExistsInDatabase{
		boardGameDAO.addBoardGame(new BoardGame("Chess", 2, 2));
		boardGameDAO.addBoardGame(new BoardGame("Tabu", 2, 8));
		boardGameDAO.addBoardGame(new BoardGame("Poszukiwacze zaginionej metody", 2, 5));
		boardGameDAO.addBoardGame(new BoardGame("Spring or not Spring", 2, 5));
		boardGameDAO.addBoardGame(new BoardGame("Wojna", 2, 12));
		boardGameDAO.addBoardGame(new BoardGame("Pozytywny feedback", 2, 2));
		boardGameDAO.addBoardGame(new BoardGame("Wielki test Junit", 2, 4));
		boardGameDAO.addBoardGame(new BoardGame("Poker", 2, 10));
		boardGameDAO.addBoardGame(new BoardGame("Makao", 2, 10));
		boardGameDAO.addBoardGame(new BoardGame("Kolko krzyzyk", 2, 2));
	}

	public void addBoardGame(BoardGame boardGame) throws BoardGameAlreadyExistsInDatabase {
		boardGameDAO.addBoardGame(boardGame);
	}
	
	public void deleteBoardGame(BoardGame boardGame) throws BoardGameAlreadyExistsInDatabase, NotExistingBoardGameException  {
		boardGameDAO.deleteBoardGame(boardGame);
	}
}
